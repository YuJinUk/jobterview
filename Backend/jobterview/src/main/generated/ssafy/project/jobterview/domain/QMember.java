package ssafy.project.jobterview.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -776233170L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMember member = new QMember("member1");

    public final QBaseTimeEntity _super = new QBaseTimeEntity(this);

    public final ListPath<Answer, QAnswer> answerList = this.<Answer, QAnswer>createList("answerList", Answer.class, QAnswer.class, PathInits.DIRECT2);

    public final ListPath<Bookmark, QBookmark> bookmarkList = this.<Bookmark, QBookmark>createList("bookmarkList", Bookmark.class, QBookmark.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final StringPath email = createString("email");

    public final NumberPath<Integer> isActive = createNumber("isActive", Integer.class);

    public final QMailCert mailCert;

    public final NumberPath<Long> memberId = createNumber("memberId", Long.class);

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final ListPath<Message, QMessage> receivedMessageList = this.<Message, QMessage>createList("receivedMessageList", Message.class, QMessage.class, PathInits.DIRECT2);

    public final StringPath refreshToken = createString("refreshToken");

    public final ListPath<Chat, QChat> roomChatList = this.<Chat, QChat>createList("roomChatList", Chat.class, QChat.class, PathInits.DIRECT2);

    public final ListPath<Message, QMessage> sentMessageList = this.<Message, QMessage>createList("sentMessageList", Message.class, QMessage.class, PathInits.DIRECT2);

    public QMember(String variable) {
        this(Member.class, forVariable(variable), INITS);
    }

    public QMember(Path<? extends Member> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMember(PathMetadata metadata, PathInits inits) {
        this(Member.class, metadata, inits);
    }

    public QMember(Class<? extends Member> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.mailCert = inits.isInitialized("mailCert") ? new QMailCert(forProperty("mailCert"), inits.get("mailCert")) : null;
    }

}

