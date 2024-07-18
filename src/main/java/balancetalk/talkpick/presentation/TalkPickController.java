package balancetalk.talkpick.presentation;

import balancetalk.global.utils.AuthPrincipal;
import balancetalk.member.dto.GuestOrApiMember;
import balancetalk.talkpick.application.TalkPickService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static balancetalk.talkpick.dto.TalkPickDto.CreateTalkPickRequest;
import static balancetalk.talkpick.dto.TalkPickDto.TalkPickDetailResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/talks")
@Tag(name = "talk_pick", description = "톡픽 API")
public class TalkPickController {

    private final TalkPickService talkPickService;

    @Operation(summary = "톡픽 생성", description = "톡픽을 생성합니다.")
    @PostMapping
    public void createTalkPick(@RequestBody final CreateTalkPickRequest request) {
    }

    @Operation(summary = "톡픽 상세 조회", description = "톡픽 상세 페이지를 조회합니다.")
    @GetMapping("/{talkPickId}")
    public TalkPickDetailResponse findTalkPickDetail(@PathVariable final Long talkPickId,
                                                     @AuthPrincipal final GuestOrApiMember guestOrApiMember) {
        return talkPickService.findById(talkPickId, guestOrApiMember);
    }

    @Operation(summary = "톡픽 수정", description = "톡픽을 수정합니다.")
    @PutMapping("/{talkPickId}")
    public void updateTalkPick(@PathVariable final Long talkPickId, @RequestBody final CreateTalkPickRequest request) {
    }

    @Operation(summary = "톡픽 삭제", description = "톡픽을 삭제합니다.")
    @DeleteMapping("/{talkPickId}")
    public void deleteTalkPick(@PathVariable final Long talkPickId) {
    }
}
