package com.elysiaptr.cemenghuiweb.repo;

import com.elysiaptr.cemenghuiweb.po.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {
}
