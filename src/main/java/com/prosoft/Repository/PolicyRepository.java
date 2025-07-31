package com.prosoft.Repository;

import com.prosoft.model.Policy;
import com.prosoft.model.PolicyState;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PolicyRepository {
    private final List<Policy> policies = new ArrayList<>();

    public void save(Policy policy) {
        policies.removeIf(p -> p.getId().equals(policy.getId()));
        policies.add(policy);
    }

    public Optional<Policy> findById(String id) {
        return policies.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public List<Policy> findByState(PolicyState state) {
        return policies.stream().filter(p -> p.getState() == state).toList();
    }

    public List<Policy> findAll() {
        return new ArrayList<>(policies);
    }
}