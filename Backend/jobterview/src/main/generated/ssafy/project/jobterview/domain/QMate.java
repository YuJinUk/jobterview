package ssafy.project.jobterview.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMate is a Querydsl query type for Mate
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMate extends EntityPathBase<Mate> {

    private static final long serialVersionUID = -992989031L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMate mate = new QMate("mate");

    public final QBaseTimeEntity _super = new QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final QMember fromMember;

    public final QMateId mateId;

    public final QMember toMember;

    public QMate(String variable) {
        this(Mate.class, forVariable(variable), INITS);
    }

    public QMate(Path<? extends Mate> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMate(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMate(PathMetadata metadata, PathInits inits) {
        this(Mate.class, metadata, inits);
    }

    public QMate(Class<? extends Mate> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.fromMember = inits.isInitialized("fromMember") ? new QMember(forProperty("fromMember"), inits.get("fromMember")) : null;
        this.mateId = inits.isInitialized("mateId") ? new QMateId(forProperty("mateId")) : null;
        this.toMember = inits.isInitialized("toMember") ? new QMember(forProperty("toMember"), inits.get("toMember")) : null;
    }

}

