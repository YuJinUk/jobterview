package ssafy.project.jobterview.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMateId is a Querydsl query type for MateId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QMateId extends BeanPath<MateId> {

    private static final long serialVersionUID = -779716716L;

    public static final QMateId mateId = new QMateId("mateId");

    public final NumberPath<Long> fromMemberId = createNumber("fromMemberId", Long.class);

    public final NumberPath<Long> toMemberId = createNumber("toMemberId", Long.class);

    public QMateId(String variable) {
        super(MateId.class, forVariable(variable));
    }

    public QMateId(Path<? extends MateId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMateId(PathMetadata metadata) {
        super(MateId.class, metadata);
    }

}

