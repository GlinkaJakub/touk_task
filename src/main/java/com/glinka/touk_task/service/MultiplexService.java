package com.glinka.touk_task.service;

import com.glinka.touk_task.model.Multiplex;
import com.glinka.touk_task.repository.MultiplexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MultiplexService implements IMultiplexService{

    MultiplexRepository multiplexRepository;

    @Autowired
    public MultiplexService(MultiplexRepository multiplexRepository) {
        this.multiplexRepository = multiplexRepository;
    }

    @Override
    public Multiplex save(Multiplex multiplex) {
        return multiplexRepository.save(multiplex);
    }
}
