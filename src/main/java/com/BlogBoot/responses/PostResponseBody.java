package com.BlogBoot.responses;

import com.BlogBoot.dto.PostBody;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostResponseBody {

    private Integer count;
    private List<PostBody> posts;
}
