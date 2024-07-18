package balancetalk.talkpick.dto;

import balancetalk.talkpick.domain.TalkPick;
import balancetalk.vote.domain.VoteOption;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

public class TalkPickDto {

    @Schema(description = "톡픽 생성/수정 요청")
    @Data
    @AllArgsConstructor
    public static class CreateTalkPickRequest {

        @Schema(description = "제목", example = "제목")
        private String title;

        @Schema(description = "본문 내용", example = "본문 내용")
        private String content;

        @Schema(description = "요약", example = "3줄 요약")
        private String summary;

        @Schema(description = "선택지 A 이름", example = "선택지 A 이름")
        private String optionA; // TODO "O"로 자동 지정

        @Schema(description = "선택지 B 이름", example = "선택지 B 이름")
        private String optionB; // TODO "X"로 자동 지정
    }

    @Schema(description = "톡픽 상세 조회 응답")
    @Data
    @AllArgsConstructor
    @Builder
    public static class TalkPickDetailResponse {

        @Schema(description = "톡픽 ID", example = "톡픽 ID")
        private Long id;

        @Schema(description = "제목", example = "톡픽 제목")
        private String title;

        @Schema(description = "본문 내용", example = "톡픽 본문 내용")
        private String content;

        private SummaryDto summary;

        @Schema(description = "선택지 A 이름", example = "선택지 A 이름")
        private String optionA; // TODO "O"로 자동 지정

        @Schema(description = "선택지 B 이름", example = "선택지 B 이름")
        private String optionB; // TODO "X"로 자동 지정

        @Schema(description = "조회수", example = "152")
        private Long views;

        @Schema(description = "북마크 여부", example = "true")
        private Boolean myBookmark;

        @Schema(description = "투표한 선택지", example = "A")
        private VoteOption votedOption;

        public static TalkPickDetailResponse from(TalkPick entity, boolean myBookmark, VoteOption votedOption) {
            return TalkPickDetailResponse.builder()
                    .id(entity.getId())
                    .title(entity.getTitle())
                    .content(entity.getContent())
                    .summary(new SummaryDto(entity.getSummary()))
                    .optionA(entity.getOptionA())
                    .optionB(entity.getOptionB())
                    .views(entity.getViews())
                    .myBookmark(myBookmark)
                    .votedOption(votedOption)
                    .build();
        }
    }
}
