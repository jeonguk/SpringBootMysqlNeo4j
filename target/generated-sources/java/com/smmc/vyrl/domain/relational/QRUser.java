package com.smmc.vyrl.domain.relational;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QRUser is a Querydsl query type for RUser
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QRUser extends EntityPathBase<RUser> {

    private static final long serialVersionUID = 1508357504L;

    public static final QRUser rUser = new QRUser("rUser");

    public final NumberPath<Integer> age = createNumber("age", Integer.class);

    public final DateTimePath<org.joda.time.DateTime> birthday = createDateTime("birthday", org.joda.time.DateTime.class);

    public final NumberPath<Integer> contact = createNumber("contact", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath email = createString("email");

    public final StringPath homepage = createString("homepage");

    public final NumberPath<java.math.BigInteger> id = createNumber("id", java.math.BigInteger.class);

    public final StringPath imagePath = createString("imagePath");

    public final StringPath nickname = createString("nickname");

    public final StringPath selfintro = createString("selfintro");

    public final StringPath socialId = createString("socialId");

    public QRUser(String variable) {
        super(RUser.class, forVariable(variable));
    }

    public QRUser(Path<? extends RUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRUser(PathMetadata metadata) {
        super(RUser.class, metadata);
    }

}

