package balancetalk.module.comment.dto;

import balancetalk.module.ViewStatus;
import balancetalk.module.comment.domain.Comment;
import balancetalk.module.member.domain.Member;
import balancetalk.module.post.domain.BalanceOption;
import balancetalk.module.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentCreateRequest {
    private String content;
    private Long memberId;
    private Long balanceOptionId;

    public Comment toEntity(Member member, Post post, BalanceOption balanceOption) {
        return Comment.builder()
                .content(content)
                .member(member)
                .post(post)
                .balanceOption(balanceOption)
                .viewStatus(ViewStatus.NORMAL)
                .build();
    }
}
