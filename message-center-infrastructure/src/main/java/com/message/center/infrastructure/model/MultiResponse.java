package com.message.center.infrastructure.model;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

/**
 * @Author linchengdong
 * @Date 2024-08-12 10:54
 * @PackageName:com.message.center.domain.model
 * @ClassName: MultiResponse
 * @Description: TODO
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MultiResponse<T> extends Response {

    private Content<T> data = new Content<>();

    public MultiResponse() {
    }

    public static <T> MultiResponse<T> of(Collection<T> data, long total) {
        MultiResponse<T> multiResponse = new MultiResponse();
        multiResponse.setSuccess(true);
        multiResponse.getData().setTotal(total);
        multiResponse.getData().setList(data);
        return multiResponse;
    }

    public static <T> MultiResponse<T> of(Page<T> page) {
        return of(page.getRecords(), page.getTotal());
    }

    public static <T> MultiResponse<T> empty() {
        MultiResponse<T> multiResponse = new MultiResponse();
        multiResponse.setSuccess(true);
        return multiResponse;
    }

    public static <T> MultiResponse<T> of(Collection<T> data) {
        MultiResponse<T> multiResponse = new MultiResponse<>();
        multiResponse.setSuccess(true);
        long total = 0L;
        if (data != null) {
            total = (long)data.size();
        }

        multiResponse.getData().setTotal(total);
        multiResponse.getData().setList(data);
        return multiResponse;
    }

    @Getter
    @Setter
    public static class Content<T> {
        private long total;
        private Collection<T> list;

        public Content() {
        }

    }
}
