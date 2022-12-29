package devkdt.devBook.service;

import devkdt.devBook.dto.*;
import devkdt.devBook.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class BookService {

    private final PostRepository postRepository;
    private final EvaluationRepository evaluationRepository;
    private final MemberRepository memberRepository;


//    @Transactional
//    public void evaluateV0(Long postId, EvaluationRequest evaluationRequest) {
//        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("customException만들기.."));
//        addEvaluation(post, evaluationRequest);
//        Evaluation evaluation = new Evaluation();
//        evaluation.addEvaluation(post);
//        evaluationRepository.save(evaluation);
//    }

    @Transactional
    public void evaluate(Long postId, Long memberId, EvaluationRequest evaluationRequest) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("customException만들기..evaluate..postfind"));

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new RuntimeException("customException만들기..evaluate..MemberFind.."));

        addEvaluation(post, evaluationRequest);
        Evaluation evaluation = new Evaluation();

        evaluation.addEvaluation(post, member);
        evaluationRepository.save(evaluation);
    }

    public BookDetailResponse detail(Long postId) {
        Post foundPost = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("customException detail페이지"));
        return new BookDetailResponse(foundPost);
    }

    private void addEvaluation(Post post, EvaluationRequest evaluationRequest) {
        if (evaluationRequest.isDevCourse()) {
            post.addDevCourse();
        } else if (evaluationRequest.isJunior()) {
            post.addJunior();
        } else {
            post.addMiddle();
        }
    }

    @Transactional
    public BookDetailResponse addPost(BookAddRequest addPostRequest) {
        Post savePost = postRepository.save(addPostRequest.toPost());
        return new BookDetailResponse(savePost);
    }

    public Post findPostById(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post findById 커스텀 에러 만들어라.."));
    }

    public void deletePostById(Long postId) {
        postRepository.deleteById(postId);
    }

    public List<Post> findAllPost() {
        return postRepository.findAll();
    }


    public PostOnePage findOnePagePost(int pageNumber) {
        PageRequest pageRequest = PageRequest.of(pageNumber, 10);

        Page<Post> posts = postRepository.findPostPage(pageRequest);

        List<PostForPage> postForPages = posts.stream()
                .map(post -> new PostForPage(post.getId(), post.getTitle()))
                .collect(Collectors.toList());
        int postCount = postForPages.size();
        int allPageCount = posts.getTotalPages();

        return new PostOnePage(postCount, allPageCount, postForPages);
    }
}
