package com.elysiaptr.cemenghuiweb.web.repo;

import com.elysiaptr.cemenghuiweb.web.po.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
}
