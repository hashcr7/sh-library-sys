package sh.library.module.book;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import sh.library.entity.BookDO;
import sh.library.mapper.BookMapper;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 根据书籍借阅数量，选出借阅数量最多的5本热门书籍，缓存在本地
 * 缓存删除和更新时,先删除缓存，再更新数据库
 * 后台线程每隔15分钟刷新一次，尽量保持最热门的书籍留在缓存中。
 * @author huangqc
 * @date 2022/9/20
 */
@Slf4j
@Service
public class BookCatch extends ServiceImpl<BookMapper, BookDO> {
    private final Integer catchSize = 5;
    private ConcurrentHashMap<Integer, BookDO> bookCatchMap = new ConcurrentHashMap(catchSize);

    /**
     * 项目启动时执行一次,之后每隔15分钟执行一次
     */
    @Scheduled(cron = "0 0/15 * * * ?")
    public void init() {
        List<BookDO> bookDOS = this.lambdaQuery().orderByDesc(BookDO::getBorrowingNum)
                .list().stream().limit(catchSize).collect(Collectors.toList());
        for (BookDO bookDO : bookDOS) {
            bookCatchMap.put(bookDO.getId(), bookDO);
        }
        log.info("当前书籍缓存数据是[{}]", bookCatchMap.entrySet());
    }

    /**
     * @param bookId 书籍Id
     * @return 书籍信息
     */
    public BookDO getBookById(@NonNull Integer bookId) {
        BookDO bookDO = bookCatchMap.get(bookId);
        if (bookDO != null) {
            log.info("本次请求命中缓存，缓存id:[{}]", bookId);
        }
        return bookCatchMap.get(bookId);
    }

    public void removeBookByid(@NonNull Integer bookId) {
        bookCatchMap.remove(bookId);
        log.info("本次从书籍缓存中移除书籍[id:{}]", bookId);
    }


}
