package ru.tinkoff.edu.service.jooq;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tinkoff.edu.converter.EntityConverter;
import ru.tinkoff.edu.domain.jooq.Tables;
import ru.tinkoff.edu.entity.Link;
import ru.tinkoff.edu.exception.InvalidInputDataException;
import ru.tinkoff.edu.service.LinkService;

@AllArgsConstructor
@Service
public class JooqLinkService implements LinkService {
    private DSLContext context;

    @Transactional
    @Override
    public Link add(Long id, URI url) throws InvalidInputDataException {
        Link link = EntityConverter.createLink(url.toString());
        context.insertInto(Tables.LINK, Tables.LINK.ID, Tables.LINK.LINKNAME, Tables.LINK.UPDATED_AT)
                .values(id.intValue(), url.toString(), link.getLastUpdateDate().toLocalDateTime()).execute();
        return null;
    }

    @Transactional
    @Override
    public Link remove(Long id, URI url) throws InvalidInputDataException {
        Link link = EntityConverter.createLink(url.toString());
        int count = context.deleteFrom(Tables.LINK)
                .where(Tables.LINK.URL.eq(url.toString()))
                .execute();
        if (count == 0) {
            throw new InvalidInputDataException();
        }
        return link;
    }

    @Override
    public List<Link> listAll() {
        return context.select(Tables.LINK.fields())
                .from(Tables.LINK)
                .fetchInto(Link.class);
    }

    @Override
    public void updateLinkData(Link link) {
        context.update(Tables.LINK).set(Tables.LINK.UPDATED_AT, link.getLastUpdateDate().toLocalDateTime())
                .where(Tables.LINK.ID.eq(link.getId().intValue()))
                .execute();
    }

    @Override
    public List<Link> getLinksForUpdate() {
        return context.select(Tables.LINK.fields())
                .from(Tables.LINK)
                .where(Tables.LINK.UPDATED_AT.lessThan(LocalDateTime.now().minusWeeks(1)))
                .fetchInto(Link.class);
    }

    private Link getLink(long linkId) {
        List<Link> links = context.select(Tables.LINK.fields())
                .from(Tables.LINK)
                .where(Tables.LINK.ID.eq((int) linkId))
                .fetchInto(Link.class);
        return links.isEmpty() ? null : links.get(0);
    }

    @Override
    public List<Link> listAll(Long chatId) {
        return null;
    }
}
