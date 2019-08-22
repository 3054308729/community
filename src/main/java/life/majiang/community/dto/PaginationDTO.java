package life.majiang.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页数据类
 * Created by hp on 2019/8/21 8:43
 */
@Data
public class PaginationDTO<T> {
    private List<T> questions;
    private boolean showPrevious = false;//是否有上一页
    private boolean showFirstPage = false;//首页
    private boolean showNext = false;//下一页
    private boolean showEndPage = false;//尾页
    private Integer page;//当前页
    private List<Integer> pages = new ArrayList<>();//显示页码条数
    private Integer totalPage;


    public void setPagination(Integer totalPage, Integer pn) {
        this.totalPage = totalPage;
        this.page = pn;
        pages.add(pn);

        for ( int i = 1; i <= 3; i++ ) {
            if (pn - i > 0) {
                pages.add(0, pn - i);
            }
            if (pn + i <= totalPage) {
                pages.add(pn + i);
            }
        }

        //判断是否为第一页
        if (pn == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }

        //判断是否为最后一页
        if (pn == totalPage) {
            showNext = false;
        } else {
            showNext = true;
        }


        //判断是否展示第一页
        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }

        //是否展示最后一页
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }
    }
}
