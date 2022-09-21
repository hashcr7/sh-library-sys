package sh.library.module.book;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import sh.library.commom.page.PageList;
import sh.library.commom.response.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sh.library.dto.request.BookInsertBody;
import sh.library.dto.request.BookQuery;
import sh.library.dto.request.BookUpdateBody;
import sh.library.dto.response.BookVO;
import sh.library.utils.SessionUtils;

import javax.validation.Valid;

/**
 * @author huangqc
 * @date 2022/9/19
 */
@Slf4j
@Api(tags = "图书接口")
@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    BookService bookService;
    @Autowired
    SessionUtils sessionUtils;

    @RequiresAuthentication
    @ApiOperation(value = "根据id查询图书", notes = "")
    @GetMapping("/{bookId}")
    public RestResult<BookVO> list(@PathVariable Integer bookId) {
        log.info("[{}-{}]查询了图书[{}]", sessionUtils.getUserName(), sessionUtils.getRole(), bookId);
        return RestResult.<BookVO>builder().ok(bookService.getBookById(bookId));
    }

    @RequiresAuthentication
    @ApiOperation(value = "查询图书列表", notes = "")
    @GetMapping("/")
    public RestResult<PageList<BookVO>> list(BookQuery bookQueryBody) {
        log.info("[{}-{}]查询了图书列表", sessionUtils.getUserName(), sessionUtils.getRole());
        return RestResult.<PageList<BookVO>>builder().ok(bookService.getBooks(bookQueryBody));
    }

    @RequiresRoles("admin")
    @ApiOperation("根据id删除图书")
    @DeleteMapping("/{bookId}")
    public RestResult<Boolean> deleteConfig(@PathVariable Integer bookId) {
        log.info("[{}-{}]删除了[id为{}]的图书", sessionUtils.getUserName(), sessionUtils.getRole(), bookId);
        return RestResult.<Boolean>builder().ok(bookService.delete(bookId));
    }

    @RequiresRoles("admin")
    @ApiOperation("更新图书")
    @PutMapping("/{bookId}")
    public RestResult<Boolean> updateConfig(@PathVariable Integer bookId,
                                            @Valid @RequestBody BookUpdateBody bookUpdateBody) {
        log.info("[{}-{}]更新了[id为{}]的图书", sessionUtils.getUserName(), sessionUtils.getRole(), null);
        return RestResult.<Boolean>builder().ok(bookService.update(bookId, bookUpdateBody));
    }

    @RequiresRoles("admin")
    @ApiOperation("新增图书")
    @PostMapping("")
    public RestResult<Integer> insertConfig(@Valid @RequestBody BookInsertBody bookInsertBody) {
        log.info("[{}-{}]新增了一本书:[{}]", sessionUtils.getUserName(), sessionUtils.getRole(), bookInsertBody);
        return RestResult.<Integer>builder().ok(bookService.insert(bookInsertBody));
    }


}
