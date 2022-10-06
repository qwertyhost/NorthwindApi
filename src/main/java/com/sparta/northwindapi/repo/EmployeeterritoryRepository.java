package com.sparta.northwindapi.repo;

import com.sparta.northwindapi.entity.Employee;
import com.sparta.northwindapi.entity.Employeeterritory;
import com.sparta.northwindapi.entity.EmployeeterritoryId;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface EmployeeterritoryRepository extends JpaRepository<Employeeterritory, EmployeeterritoryId> {
    public ArrayList<Employee> list = null;
    public static EmployeeterritoryRepository repo = null;

    public static EmployeeterritoryRepository getInstance() {
        if (repo == null) {
            new EmployeeterritoryRepository() {

                @Override
                public List<Employeeterritory> findAll() {
                    List a = list;
                    return a;
                }

                @Override
                public List<Employeeterritory> findAll(Sort sort) {
                    return null;
                }

                @Override
                public Page<Employeeterritory> findAll(Pageable pageable) {
                    return null;
                }

                @Override
                public List<Employeeterritory> findAllById(Iterable<EmployeeterritoryId> employeeterritoryIds) {
                    List<Employeeterritory> findList = null;
                    for (EmployeeterritoryId a:employeeterritoryIds)
                    {
                        //findList.add(list.get(a.getTerritoryID()));
                    }
                    return findList;
                }

                @Override
                public long count() {
                    return list.size();
                }

                @Override
                public void deleteById(EmployeeterritoryId employeeterritoryId) {
                    list.remove(employeeterritoryId);
                }

                @Override
                public void delete(Employeeterritory entity) {
                    list.remove(entity);
                }

                @Override
                public void deleteAllById(Iterable<? extends EmployeeterritoryId> employeeterritoryIds) {
                    list.removeAll((Collection<?>) employeeterritoryIds);
                }

                @Override
                public void deleteAll(Iterable<? extends Employeeterritory> entities) {
                    list.removeAll((Collection<?>) entities);
                }

                @Override
                public void deleteAll() {

                }

                @Override
                public <S extends Employeeterritory> S save(S entity) {
                    return null;
                }

                @Override
                public <S extends Employeeterritory> List<S> saveAll(Iterable<S> entities) {
                    return null;
                }

                @Override
                public Optional<Employeeterritory> findById(EmployeeterritoryId employeeterritoryId) {
                    return Optional.empty();
                }

                @Override
                public boolean existsById(EmployeeterritoryId employeeterritoryId) {
                    return false;
                }

                @Override
                public void flush() {

                }

                @Override
                public <S extends Employeeterritory> S saveAndFlush(S entity) {
                    return null;
                }

                @Override
                public <S extends Employeeterritory> List<S> saveAllAndFlush(Iterable<S> entities) {
                    return null;
                }

                @Override
                public void deleteAllInBatch(Iterable<Employeeterritory> entities) {

                }

                @Override
                public void deleteAllByIdInBatch(Iterable<EmployeeterritoryId> employeeterritoryIds) {

                }

                @Override
                public void deleteAllInBatch() {

                }

                @Override
                public Employeeterritory getOne(EmployeeterritoryId employeeterritoryId) {
                    return null;
                }

                @Override
                public Employeeterritory getById(EmployeeterritoryId employeeterritoryId) {
                    return null;
                }

                @Override
                public Employeeterritory getReferenceById(EmployeeterritoryId employeeterritoryId) {
                    return null;
                }

                @Override
                public <S extends Employeeterritory> Optional<S> findOne(Example<S> example) {
                    return Optional.empty();
                }

                @Override
                public <S extends Employeeterritory> List<S> findAll(Example<S> example) {
                    return null;
                }

                @Override
                public <S extends Employeeterritory> List<S> findAll(Example<S> example, Sort sort) {
                    return null;
                }

                @Override
                public <S extends Employeeterritory> Page<S> findAll(Example<S> example, Pageable pageable) {
                    return null;
                }

                @Override
                public <S extends Employeeterritory> long count(Example<S> example) {
                    return 0;
                }

                @Override
                public <S extends Employeeterritory> boolean exists(Example<S> example) {
                    return false;
                }

                @Override
                public <S extends Employeeterritory, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                    return null;
                }
            };
        }
        return repo;
    }
}