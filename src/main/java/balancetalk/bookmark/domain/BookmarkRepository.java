package balancetalk.bookmark.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    @Query("SELECT b FROM Bookmark b WHERE b.member.id = :memberId AND b.bookmarkType = :bookmarkType AND b.active = true ORDER BY b.lastModifiedAt DESC")
    List<Bookmark> findAllByMemberId(@Param("memberId") Long memberId, @Param("bookmarkType") BookmarkType bookmarkType);

}
