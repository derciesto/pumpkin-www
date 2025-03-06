package com.ciesto.repository.senctioned;

import com.ciesto.model.senctioned.StatementDownload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatementDownloadRepository extends JpaRepository<StatementDownload, Long> {
}
