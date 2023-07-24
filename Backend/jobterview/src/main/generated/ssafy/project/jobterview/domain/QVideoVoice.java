package ssafy.project.jobterview.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QVideoVoice is a Querydsl query type for VideoVoice
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVideoVoice extends EntityPathBase<VideoVoice> {

    private static final long serialVersionUID = 780475403L;

    public static final QVideoVoice videoVoice = new QVideoVoice("videoVoice");

    public final StringPath userEmail = createString("userEmail");

    public final ArrayPath<byte[], Byte> video = createArray("video", byte[].class);

    public final NumberPath<Long> videoId = createNumber("videoId", Long.class);

    public final ArrayPath<byte[], Byte> voice = createArray("voice", byte[].class);

    public QVideoVoice(String variable) {
        super(VideoVoice.class, forVariable(variable));
    }

    public QVideoVoice(Path<? extends VideoVoice> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVideoVoice(PathMetadata metadata) {
        super(VideoVoice.class, metadata);
    }

}

