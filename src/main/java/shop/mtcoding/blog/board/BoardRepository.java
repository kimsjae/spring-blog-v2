package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import shop.mtcoding.blog.user.User;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final EntityManager em;

    public List<Board> findAllV3(){
        String q1 = "select b from Board b order by b.id desc";
        List<Board> boardList = em.createQuery(q1 , Board.class).getResultList();

        int[] userIds = boardList.stream().mapToInt(board -> board.getUser().getId()).distinct().toArray();

        String q2 = "select u from User u where u.id in (";
        for (int i = 0; i < userIds.length ; i++) {
            if (i == userIds.length - 1){
                q2 = q2 + userIds[i] + ")";
            }else {
                q2 = q2 + userIds[i] + ",";
            }
        }
        List<User> userList = em.createQuery(q2 , User.class).getResultList();

        for (Board board : boardList){
            for (User user : userList) {
                if (user.getId() == board.getUser().getId()){
                    board.setUser(user);
                }
            }
        }

        return boardList; // user가 채워져 있어야함.
    }

    public List<Board> findAllV2() { // findAll은 쿼리를 작성해야 함.
        // 1. board 뽑아냄
        String q1 = "select b from Board b order by b.id desc";
        List<Board> boardList = em.createQuery(q1, Board.class).getResultList();

        // 2. userId 찾기
        int[] userIds = boardList.stream().mapToInt(board -> board.getUser().getId()).distinct().toArray();
        int user1 = userIds[2];
        int user2 = userIds[1];
        int user3 = userIds[0];
        String q2 = "select u from User u where u.id in (:id1, :id2, :id3)"; // in쿼리
        q2 = q2.replace(":id1", user3+"").replace(":id2", user2+"").replace(":id3", user1+"");
        List<User> userList = em.createQuery(q2, User.class).getResultList();

        // SELECT b, u FROM Board b LEFT JOIN b.user u
        for (Board board : boardList){
            for (User user : userList) {
                if (user.getId() == board.getUser().getId()){
                    board.setUser(user);
                }
            }
        }


        return boardList; // user가 채워져 있어야 한다.
    }

    public List<Board> findAll() { // findAll은 쿼리를 작성해야 함.
        Query query = em.createQuery("select b from Board b order by b.id desc", Board.class);
        return query.getResultList();
    }

    public Board findByIdJoinUser(int id) {
        Query query = em.createQuery("select b from Board b join fetch b.user u where b.id = :id", Board.class);
        query.setParameter("id", id);
        return (Board) query.getSingleResult();
    }

    public Board findById(int id) {
        // id, title, content, user_id(이질감),
        Board board = em.find(Board.class, id);
        return board;
    }
}
