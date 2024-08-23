package org.arta.vopros.db.impl;

import org.arta.vopros.db.Dao;
import org.arta.vopros.domain.Tag;
import org.arta.vopros.domain.User;

import java.util.List;

public class TagDao implements Dao<Tag, Long> {
    private static final TagDao INSTANCE = new TagDao();

    private TagDao() {}

    public TagDao getInstance() {
        return INSTANCE;
    }

    @Override
    public Tag save(Tag tag) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean update(Tag tag) {
        return false;
    }

    @Override
    public Tag findById(Long id) {
        return null;
    }

    @Override
    public List<Tag> findAll() {
        return null;
    }
}
