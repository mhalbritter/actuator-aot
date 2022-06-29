package com.example.actuator_aot;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.MapSession;
import org.springframework.stereotype.Component;

/**
 * @author Moritz Halbritter
 */
@Component
class MyFindByIndexNameSessionRepository implements FindByIndexNameSessionRepository<MapSession> {
    private final Map<String, MapSession> sessions = new ConcurrentHashMap<>();

    @Override
    public Map<String, MapSession> findByIndexNameAndIndexValue(String indexName, String indexValue) {
        return this.sessions;
    }

    @Override
    public MapSession createSession() {
        return new MapSession();
    }

    @Override
    public void save(MapSession session) {
        this.sessions.put(session.getId(), session);
    }

    @Override
    public MapSession findById(String id) {
        return this.sessions.get(id);
    }

    @Override
    public void deleteById(String id) {
        this.sessions.remove(id);
    }
}
