package com.ciesto.repository;

import com.ciesto.model.StatementDownload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StatementDownloadRepository extends JpaRepository<StatementDownload, Long> {
}
