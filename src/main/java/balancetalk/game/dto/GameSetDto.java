package balancetalk.game.dto;

import balancetalk.bookmark.domain.GameBookmark;
import balancetalk.game.domain.Game;
import balancetalk.game.domain.GameSet;
import balancetalk.game.domain.MainTag;
import balancetalk.game.dto.GameDto.CreateOrUpdateGame;
import balancetalk.game.dto.GameDto.GameDetailResponse;
import balancetalk.member.domain.Member;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

public class GameSetDto {

    @Data
    public static class CreateGameSetRequest {

        @Schema(description = "밸런스게임 세트 제목", example = "밸런스게임 세트 제목")
        private String title;

        @Schema(description = "밸런스 게임 메인 태그", example = "커플")
        private String mainTag;

        @Schema(description = "밸런스 게임 서브 태그", example = "커플지옥")
        private String subTag;

        private List<CreateOrUpdateGame> games;

        public GameSet toEntity(String title, MainTag mainTag, Member member) {
            return GameSet.builder()
                    .title(title)
                    .mainTag(mainTag)
                    .subTag(subTag)
                    .member(member)
                    .games(games.stream().map(CreateOrUpdateGame::toEntity).toList())
                    .bookmarks(0L)
                    .editedAt(LocalDateTime.now())
                    .build();
        }

        public List<String> extractAllStoredNames() {
            return games.stream()
                    .flatMap(game -> game.extractStoresNames().stream())
                    .toList();
        }
    }

    @Data
    @Builder
    @AllArgsConstructor
    @Schema(description = "밸런스 게임 세트 목록 조회 응답")
    public static class GameSetResponse {

        @Schema(description = "밸런스 게임 세트 id", example = "1")
        private Long id;

        @Schema(description = "밸런스 게임 제목", example = "제목")
        private String title;

        @Schema(description = "메인 태그", example = "사랑")
        private String mainTag;

        @Schema(description = "서브 태그", example = "서브 태그")
        private String subTag;

        private List<String> images;

        public static GameSetResponse fromEntity(GameSet gameSet) {
            Game game = gameSet.getGames().get(0);
            List<String> images = new ArrayList<>();
            images.addAll(Arrays.asList(
                    game.getGameOptions().get(0).getImgUrl(),
                    game.getGameOptions().get(1).getImgUrl()
            ));
            return GameSetResponse.builder()
                    .id(gameSet.getId())
                    .title(gameSet.getTitle())
                    .mainTag(gameSet.getMainTag().getName())
                    .subTag(gameSet.getSubTag())
                    .images(images)
                    .build();
        }
    }

    @Data
    @Builder
    @AllArgsConstructor
    @Schema(description = "밸런스 게임 세트 상세 조회 응답")
    public static class GameSetDetailResponse {

        @Schema(description = "작성자", example = "멤버")
        private String member;

        @Schema(description = "밸런스게임 세트 제목", example = "밸런스게임 세트 제목")
        private String title;

        @Schema(description = "작성 날짜", example = "2024-10-07T21:55:19.591909")
        private LocalDateTime createdAt;

        @Schema(description = "메인 태그", example = "사랑")
        private String mainTag;

        @Schema(description = "서브 태그", example = "서브 태그")
        private String subTag;

        private List<GameDetailResponse> gameDetailResponses;

        @Schema(description = "밸런스게임 세트 전체 투표 완료 여부", example = "false")
        @JsonProperty("isEndGameSet")
        private boolean isEndGameSet;

        @Schema(description = "밸런스게임 세트 엔딩 페이지에 사용되는 북마크 filled 여부", example = "false")
        @JsonProperty("isEndBookmarked")
        private boolean isEndBookmarked;

        public static GameSetDetailResponse fromEntity(GameSet gameSet, GameBookmark gameBookmark, boolean isEndGameSet,
                                                       List<GameDetailResponse> gameDetailResponses) {

            return GameSetDetailResponse.builder()
                    .member(gameSet.getMember().getNickname())
                    .title(gameSet.getTitle())
                    .createdAt(gameSet.getCreatedAt())
                    .mainTag(gameSet.getMainTag().getName())
                    .subTag(gameSet.getSubTag())
                    .isEndGameSet(isEndGameSet)
                    .isEndBookmarked(gameBookmark != null && gameBookmark.isActive())
                    .gameDetailResponses(gameDetailResponses)
                    .build();
        }
    }
}
