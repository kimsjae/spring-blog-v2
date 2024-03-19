package shop.mtcoding.blog.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardJPARepository extends JpaRepository<Board, Integer> {

    @Query("select b from Board b join fetch b.user u where b.id = :id")
    Optional<Board> findByIdJoinUser(@Param("id") int id); // 한 건만 받을 땐 @Param을 안 해도 됨.

    @Query("select b from Board b join fetch b.user u left join fetch b.replies r where b.id = :id")
    // 댓글이 없는 게시물이 안 보일 수도 있으니까 left join 해주자.
    Optional<Board> findByIdJoinUserAndReplies(@Param("id") int id); // 한 건만 받을 땐 @Param을 안 해도 됨.

    // 위에 것으로 쓰자. 빠른 건 같지만 더 쉽다.
}
