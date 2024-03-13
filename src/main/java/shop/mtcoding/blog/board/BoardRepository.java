package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final EntityManager em;

    @Transactional
    public void updateById(int id, BoardRequest.UpdateDTO requestDTO) {
        Board board = findById(id);
        board.update(requestDTO);
    } // 더티체킹

    @Transactional
    public void updateById(String title, String content, String username, Integer id) {
        Query query = em.createNativeQuery("update board_tb set title = ?, content = ?, username = ? where id = ?");
        query.setParameter(1, title);
        query.setParameter(2, content);
        query.setParameter(3, username);
        query.setParameter(4, id);
        query.executeUpdate();
    }

    @Transactional
    public void deleteById(int id) {
        Query query = em.createQuery("delete from Board b where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Transactional
    public void deleteByIdV2(int id) {
        Board board = findById(id);
        em.remove(board); // PC에 객체 지우고, 트랜잭션 종료 시 삭제 쿼리를 전송함.
        // 다양한 예외가 있는데 재사용할 수 있는 여기에 비지니스 로직을 짜는 건 추천하지 않는다.
    }

    public Board findById(int id) {
        Board board = em.find(Board.class, id); // 한 건을 받을 땐 find. 뭐로 받을건지, PK.
        return board;
    }


    public List<Board> findAll() {
        Query query = em.createQuery("select b from Board b order by b.id desc", Board.class);
        return query.getResultList();
    }

    @Transactional
    public Board save(Board board) {
        // 1. 비영속 객체
        em.persist(board);
        // 2. board -> 영속 객체
        return board;
    }
}
