package com.sparta.northwindapi.repo;

import com.sparta.northwindapi.entity.Employee;
import com.sparta.northwindapi.entity.Order;
import com.sparta.northwindapi.entity.Territory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface TerritoryRepository extends JpaRepository<Territory, String> {
    public ArrayList<Employee> list = null;
    public static TerritoryRepository repo = null;

    public static TerritoryRepository getInstance() {
        if (repo == null) {
            new TerritoryRepository() {

                @Override
                public List<Territory> findAll() {
                    return null;
                }

                @Override
                public List<Territory> findAll(Sort sort) {
                    return null;
                }

                @Override
                public Page<Territory> findAll(Pageable pageable) {
                    return null;
                }

                @Override
                public List<Territory> findAllById(Iterable<String> strings) {
                    return null;
                }

                @Override
                public long count() {
                    return 0;
                }

                @Override
                public void deleteById(String s) {

                }

                @Override
                public void delete(Territory entity) {

                }

                @Override
                public void deleteAllById(Iterable<? extends String> strings) {

                }

                @Override
                public void deleteAll(Iterable<? extends Territory> entities) {

                }

                @Override
                public void deleteAll() {

                }

                @Override
                public <S extends Territory> S save(S entity) {
                    return null;
                }

                @Override
                public <S extends Territory> List<S> saveAll(Iterable<S> entities) {
                    return null;
                }

                @Override
                public Optional<Territory> findById(String s) {
                    return Optional.empty();
                }

                @Override
                public boolean existsById(String s) {
                    return false;
                }

                @Override
                public void flush() {

                }

                @Override
                public <S extends Territory> S saveAndFlush(S entity) {
                    return null;
                }

                @Override
                public <S extends Territory> List<S> saveAllAndFlush(Iterable<S> entities) {
                    return null;
                }

                @Override
                public void deleteAllInBatch(Iterable<Territory> entities) {

                }

                @Override
                public void deleteAllByIdInBatch(Iterable<String> strings) {

                }

                @Override
                public void deleteAllInBatch() {

                }

                @Override
                public Territory getOne(String s) {
                    return null;
                }

                @Override
                public Territory getById(String s) {
                    return null;
                }

                @Override
                public Territory getReferenceById(String s) {
                    return null;
                }

                @Override
                public <S extends Territory> Optional<S> findOne(Example<S> example) {
                    return Optional.empty();
                }

                @Override
                public <S extends Territory> List<S> findAll(Example<S> example) {
                    return null;
                }

                @Override
                public <S extends Territory> List<S> findAll(Example<S> example, Sort sort) {
                    return null;
                }

                @Override
                public <S extends Territory> Page<S> findAll(Example<S> example, Pageable pageable) {
                    return null;
                }

                @Override
                public <S extends Territory> long count(Example<S> example) {
                    return 0;
                }

                @Override
                public <S extends Territory> boolean exists(Example<S> example) {
                    return false;
                }

                @Override
                public <S extends Territory, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                    return null;
                }
            };
        }
        return repo;
    }
}