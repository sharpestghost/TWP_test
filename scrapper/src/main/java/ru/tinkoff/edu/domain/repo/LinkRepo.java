package ru.tinkoff.edu.domain.repo;

import java.util.List;
import org.springframework.stereotype.Repository;
import ru.tinkoff.edu.entity.Link;
import ru.tinkoff.edu.exception.ResultNotFoundException;

@Repository
public interface LinkRepo extends QueryRepo<Link> {
    Link add(Link url);

    void remove(String url);

    void updateLinkData(long linkId);

    Link getByLink(String link) throws ResultNotFoundException;

    List<Link> getOldLinksListForUpdate();
}
