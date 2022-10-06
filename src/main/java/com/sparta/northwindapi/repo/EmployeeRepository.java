package com.sparta.northwindapi.repo;

import com.sparta.northwindapi.entity.Employee;
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

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    public ArrayList<Employee> list = null;
    public static EmployeeRepository repo = null;

    public static EmployeeRepository getInstance() {
        if (repo == null) {
            new EmployeeRepository() {
                @Override
                public List<Employee> findAll() {
                    return list;
                }

                @Override
                public List<Employee> findAll(Sort sort) {
                    return list;
                }

                @Override
                public Page<Employee> findAll(Pageable pageable) {
                    return (Page<Employee>) list;
                }

                @Override
                public List<Employee> findAllById(Iterable<Integer> integers) {
                    List<Employee> employeeList = null;
                    for(Integer a:integers)
                    {
                        employeeList.add(list.get(a));
                    }
                    return employeeList;
                }

                @Override
                public long count() {
                    return list.size();
                }

                @Override
                public void deleteById(Integer integer) {
                    list.remove(integer);
                }

                @Override
                public void delete(Employee entity) {
                    list.remove(entity);
                }

                public void add(Employee entity) {
                    list.add(entity);
                }
                public void add(Employee entity, Integer integer) {
                    list.add(integer,entity);
                }

                @Override
                public void deleteAllById(Iterable<? extends Integer> integers) {
                    list.removeAll((Collection<?>) integers);
                }

                @Override
                public void deleteAll(Iterable<? extends Employee> entities) {
                    list.removeAll((Collection<?>) entities);
                }

                @Override
                public void deleteAll() {
                    list.clear();
                }

                @Override
                public <S extends Employee> S save(S entity) {
                    return repo.save(entity);
                }
                @Override
                public <S extends Employee> List<S> saveAll(Iterable<S> entities) {
                    return repo.saveAll(entities);
                }

                @Override
                public Optional<Employee> findById(Integer integer) {
                    return Optional.of(list.get(integer));
                }

                @Override
                public boolean existsById(Integer integer) {
                    if(list.contains(integer))
                        return true;
                    return false;
                }

                @Override
                public void flush() {
                    repo.flush();
                }

                @Override
                public <S extends Employee> S saveAndFlush(S entity) {
                    repo.flush();
                    return repo.save(entity);
                }

                @Override
                public <S extends Employee> List<S> saveAllAndFlush(Iterable<S> entities) {
                    repo.flush();
                    return repo.saveAll(entities);
                }

                @Override
                public void deleteAllInBatch(Iterable<Employee> entities) {
                    list.removeAll((Collection<?>) entities);
                }

                @Override
                public void deleteAllByIdInBatch(Iterable<Integer> integers) {
                    list.removeAll((Collection<?>) integers);
                }

                @Override
                public void deleteAllInBatch() {
                    list.clear();
                }

                @Override
                public Employee getOne(Integer integer) {
                    return list.get(integer);
                }

                @Override
                public Employee getById(Integer integer) {
                    return list.get(integer);
                }

                @Override
                public Employee getReferenceById(Integer integer) {
                    return list.get(integer);
                }

                @Override
                public <S extends Employee> Optional<S> findOne(Example<S> example) {
                    return Optional.of(example.getProbe());
                }

                @Override
                public <S extends Employee> List<S> findAll(Example<S> example) {
                    List employeeList = null;
                    employeeList.add(Optional.of(example.getProbe()));
                    return employeeList;
                }

                @Override
                public <S extends Employee> List<S> findAll(Example<S> example, Sort sort) {
                    List employeeList = null;
                    employeeList.add(sort.equals(example));
                    return employeeList;
                }

                @Override
                public <S extends Employee> Page<S> findAll(Example<S> example, Pageable pageable) {
                    return null;
                }

                @Override
                public <S extends Employee> long count(Example<S> example) {
                    return 0;
                }

                @Override
                public <S extends Employee> boolean exists(Example<S> example) {
                    if(list.contains(example))
                        return true;
                    return false;
                }

                @Override
                public <S extends Employee, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                    return null;
                }
            };
        }
        return repo;
    }
}