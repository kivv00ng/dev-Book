package devkdt.devBook.entity;

import javax.persistence.*;

@Entity
public class Evaluation {

    @Id
    @GeneratedValue
    @Column(name = "evaluation_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public void addEvaluation(Post post, Member member) {
        this.post = post;
        post.getEvaluations().add(this);

        //회원가입 추가시 Member와 함께..
        this.member = member;
        member.getEvaluations().add(this);
    }

}
