package sh.library.module.book;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sh.library.commom.page.PageList;
import sh.library.commom.response.ServiceRuntimeException;
import sh.library.dto.request.BookInsertBody;
import sh.library.dto.request.BookQuery;
import sh.library.dto.request.BookUpdateBody;
import sh.library.dto.response.BookVO;
import sh.library.entity.BookDO;
import sh.library.exception.ErrorCode;
import sh.library.mapper.BookMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 书籍增删改查
 *
 * @author huangqc
 * @date 2022/9/19
 */
@Slf4j
@Service
public class BookService extends ServiceImpl<BookMapper, BookDO> {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    BookCatch bookCatch;

    /**
     * 根据书籍id,获取书籍信息
     *
     * @param bookId 书籍主键id
     * @return 书籍信息
     */
    public BookVO getBookById(Integer bookId) {
        BookDO bookDO = bookCatch.getBookById(bookId);
        if (bookDO != null) {
            return modelMapper.map(bookDO, BookVO.class);
        }
        BookDO one = this.lambdaQuery().eq(BookDO::getId, bookId).one();

        if (one == null) {
            throw ServiceRuntimeException.build(ErrorCode.BOOK_IS_BULL);
        }
        BookVO bookVO = modelMapper.map(one, BookVO.class);
        return bookVO;
    }


    /**
     * 分页查询书籍列表
     *
     * @param bookQuery 书籍列表查询条件
     * @return 书籍列表
     */
    public PageList<BookVO> getBooks(BookQuery bookQuery) {
        List<BookVO> listsVO = new ArrayList<>();
        List<BookDO> listsDo = this.lambdaQuery().list().stream()
                .skip(bookQuery.getPageNum()-1).limit(bookQuery.getPageSize())
                .collect(Collectors.toList());

        for (BookDO bookDO : listsDo) {
            BookVO bookVO = modelMapper.map(bookDO, BookVO.class);
            listsVO.add(bookVO);
        }

        Page<BookVO> page = new Page<BookVO>(bookQuery.getPageNum(),
                bookQuery.getPageSize(), listsVO.size());
        return PageList.<BookVO>builder().pages((int) page.getPages())
                .total(page.getTotal())
                .pageSize((int) page.getSize())
                .pageNum((int) page.getCurrent())
                .list(listsVO).build();
    }

    /**
     * 删除书籍
     *
     * @param bookId 书籍id
     * @return 是否删除成功
     */
    public Boolean delete(Integer bookId) {
        bookCatch.removeBookByid(bookId);
        return this.removeById(bookId);
    }

    /**
     * 更新书籍
     *
     * @param bookId         书籍id
     * @param bookUpdateBody 书籍更新内容
     * @return 是否更新成功
     */
    public Boolean update(Integer bookId, BookUpdateBody bookUpdateBody) {
        bookCatch.removeBookByid(bookId);
        BookDO bookDO = modelMapper.map(bookUpdateBody, BookDO.class);
        bookDO.setId(bookId);
        return this.updateById(bookDO);
    }

    /**
     * 新增书籍
     *
     * @param bookInsertBody 新增书籍信息
     * @return 新增后的书籍id
     */
    public Integer insert(BookInsertBody bookInsertBody) {
        BookDO one = this.lambdaQuery()
                .eq(BookDO::getAuthor, bookInsertBody.getAuthor())
                .eq(BookDO::getName, bookInsertBody.getName()).one();
        if (one != null) {
            throw ServiceRuntimeException.build(ErrorCode.BOOK_EXISTS);
        }
        BookDO bookDO = modelMapper.map(bookInsertBody, BookDO.class);
        this.save(bookDO);
        return bookDO.getId();

    }
}
