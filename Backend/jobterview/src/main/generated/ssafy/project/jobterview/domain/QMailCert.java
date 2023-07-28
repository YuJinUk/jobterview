package ssafy.project.jobterview.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMailCert is a Querydsl query type for MailCert
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMailCert extends EntityPathBase<MailCert> {

    private static final long serialVersionUID = 2002882575L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMailCert mailCert = new QMailCert("mailCert");

    public final QBaseTimeEntity _super = new QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final BooleanPath mailAuth = createBoolean("mailAuth");

    public final NumberPath<Long> mailCertId = createNumber("mailCertId", Long.class);

    public final NumberPath<Integer> mailKey = createNumber("mailKey", Integer.class);

    public final QMember member;

    public QMailCert(String variable) {
        this(MailCert.class, forVariable(variable), INITS);
    }

    public QMailCert(Path<? extends MailCert> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMailCert(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMailCert(PathMetadata metadata, PathInits inits) {
        this(MailCert.class, metadata, inits);
    }

    public QMailCert(Class<? extends MailCert> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
    }

}

