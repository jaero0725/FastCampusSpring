package com.example.restaurant.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class MemoryDbRepositoryAbstract<T extends MemoryDbEntity> implements MemoryDbRepositoryIfs <T> {

    private final List<T> db = new ArrayList<>();   //List에 넣는 형태로 함.
    private int index = 0;

    @Override
    public Optional<T> findById(int index) {
        return db.stream().filter(it -> it.getIndex() == index ).findFirst();      //stream을 통해
    }

    @Override
    public T save(T entity) {

        var optionalEntity = db.stream().filter(it -> it.getIndex() == entity.getIndex()).findFirst();

        if(optionalEntity.isEmpty()){
            // db에 이미 데이터가 있는 경우
            index++;
            entity.setIndex(index);
            db.add(entity);
            return entity;
        }else{
            // db에 데이터가 없는 경우
            var preIndex = optionalEntity.get().getIndex();
            entity.setIndex(preIndex);

            deleteById(preIndex);
            db.add(entity);
            return entity;
        }
    }

    @Override
    public void deleteById(int index) {
        var optionalEntity = db.stream().filter(it -> it.getIndex() == index).findFirst();
        optionalEntity.ifPresent(db::remove);
    }

    @Override
    public List<T> findAll() {
        return db;
    }

}
