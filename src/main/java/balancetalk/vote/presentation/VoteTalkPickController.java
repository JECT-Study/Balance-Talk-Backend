package balancetalk.vote.presentation;

import balancetalk.global.utils.AuthPrincipal;
import balancetalk.member.dto.ApiMember;
import balancetalk.member.dto.GuestOrApiMember;
import balancetalk.vote.application.VoteTalkPickService;
import balancetalk.vote.dto.VoteTalkPickDto.VoteResultResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static balancetalk.vote.dto.VoteTalkPickDto.VoteRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/votes/talks/{talkPickId}")
@Tag(name = "vote", description = "투표 API")
public class VoteTalkPickController {

    private final VoteTalkPickService voteTalkPickService;

    @Operation(summary = "톡픽 투표 생성", description = "톡픽에서 원하는 선택지에 투표합니다.")
    @PostMapping

    public void createVoteTalkPick(@PathVariable long talkPickId,
                                   @RequestBody VoteRequest request,
                                   @AuthPrincipal GuestOrApiMember guestOrApiMember) {
        voteTalkPickService.createVote(talkPickId, request, guestOrApiMember);
    }

    // TODO 톡픽 조회에 포함하기
    @Operation(summary = "톡픽 투표 결과 조회", description = "톡픽 투표 결과를 조회합니다.")
    @GetMapping
    public VoteResultResponse getVoteResultTalkPick(@PathVariable Long talkPickId) {
        return new VoteResultResponse(23, 12);
    }

    @Operation(summary = "톡픽 투표 수정", description = "톡픽 투표를 수정합니다.")
    @PutMapping
    public void updateVoteResultTalkPick(@PathVariable long talkPickId,
                                         @RequestBody VoteRequest request,
                                         @AuthPrincipal ApiMember apiMember) {
        voteTalkPickService.updateVote(talkPickId, request, apiMember);
    }

    @Operation(summary = "톡픽 투표 삭제", description = "톡픽 투표를 삭제합니다.")
    @DeleteMapping
    public void deleteVoteTalkPick(@PathVariable long talkPickId,
                                   @AuthPrincipal ApiMember apiMember) {
        voteTalkPickService.deleteVote(talkPickId, apiMember);
    }
}
