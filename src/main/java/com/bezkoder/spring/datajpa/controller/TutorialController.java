package com.bezkoder.spring.datajpa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.spring.datajpa.model.Faculty;
import com.bezkoder.spring.datajpa.model.Login;
import com.bezkoder.spring.datajpa.model.Student;
import com.bezkoder.spring.datajpa.model.Subject;
import com.bezkoder.spring.datajpa.model.Tutorial;
import com.bezkoder.spring.datajpa.repository.FacultyRepository;
import com.bezkoder.spring.datajpa.repository.LoginRepository;
import com.bezkoder.spring.datajpa.repository.StudentRepository;
import com.bezkoder.spring.datajpa.repository.SubjectRepository;
import com.bezkoder.spring.datajpa.repository.TutorialRepository;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TutorialController {

	@Autowired
	TutorialRepository tutorialRepository;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	FacultyRepository facultyRepository;
	
	@Autowired
	SubjectRepository subjectRepository;
	
	@Autowired
	LoginRepository loginRepository;
	
	/////////////////CREATE  LOGIN ////////////////
	///
	@PostMapping("/login")
	public ResponseEntity<Login> createLogin(@RequestBody Login login) {
		try {
			Login _login = loginRepository
					.save(new Login(login.getUsername(), login.getPassword(), false));
			return new ResponseEntity<>(_login, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
///////////////////////////////////////////////////////
	@GetMapping("/tutorials")
	public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
		try {
			
			List<Tutorial> tutorials = new ArrayList<Tutorial>();
			List<Tutorial> tutorials2 = new ArrayList<Tutorial>();

			if (title == null)
				tutorialRepository.findAll().forEach(tutorials::add);
			else
				tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);

			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/tutorials/{id}")
	public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
		Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

		if (tutorialData.isPresent()) {
			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/tutorials")
	public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
		try {
			Tutorial _tutorial = tutorialRepository
					.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), false));
			return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	///////////getall and getbyid for students////////////////////////
	
	@GetMapping("/students")
	public ResponseEntity<List<Student>> getAllStudents(@RequestParam(required = false) String name) {
		try {
			List<Student> students = new ArrayList<Student>();

			
			if (name == null)
				studentRepository.findAll().forEach(students::add);
			else
				studentRepository.findByNameContaining(name).forEach(students::add);

			if (students.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(students, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/students/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable("id") long id) {
		Optional<Student> studentData = studentRepository.findById(id);

		if (studentData.isPresent()) {
			return new ResponseEntity<>(studentData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	
    ///////////getall and getid for students////////////////////////
	
	/////////Update student /delete student/////////////////////
	@PutMapping("/students/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable("id") long id, @RequestBody Student student) {
		Optional<Student> studentData = studentRepository.findById(id);

		if (studentData.isPresent()) {
			Student _student = studentData.get();
			_student.setName(student.getName());
			_student.setCourse(student.getCourse());
			_student.setStream(student.isStream());
			return new ResponseEntity<>(studentRepository.save(_student), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/students/{id}")
	public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") long id) {
		try {
			studentRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/students")
	public ResponseEntity<HttpStatus> deleteAllstudents() {
		try {
			studentRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/students/stream")
	public ResponseEntity<List<Student>> findByStream() {
		try {
			List<Student> students = studentRepository.findByStream(true);

			if (students.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(students, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	
	///////////end update/delete student///////////////////////////
	
	/////////////delete update faculty data///////////////////
	@PutMapping("/faculties/{id}")
	public ResponseEntity<Faculty> updateFaculty(@PathVariable("id") long id, @RequestBody Faculty faculty) {
		Optional<Faculty> facultyData = facultyRepository.findById(id);

		if (facultyData.isPresent()) {
			Faculty _faculty = facultyData.get();
			_faculty.setFacultyname(faculty.getFacultyname());
			_faculty.setSubject(faculty.getSubject());
			_faculty.setEmployee(faculty.isEmployee());
			return new ResponseEntity<>(facultyRepository.save(_faculty), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/faculties/{id}")
	public ResponseEntity<HttpStatus> deleteFaculty(@PathVariable("id") long id) {
		try {
			facultyRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/faculties")
	public ResponseEntity<HttpStatus> deleteAllfaculty() {
		try {
			facultyRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/faculties/employee")
	public ResponseEntity<List<Faculty>> findByEmployee() {
		try {
			List<Faculty> faculty = facultyRepository.findByEmployee(true);

			if (faculty.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(faculty, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/faculties")
	public ResponseEntity<List<Faculty>> getAllFaculty(@RequestParam(required = false) String facultyname) {
		try {
			List<Faculty> faculty = new ArrayList<Faculty>();

			if (facultyname == null)
				facultyRepository.findAll().forEach(faculty::add);
			else
				facultyRepository.findByFacultynameContaining(facultyname).forEach(faculty::add);

			if (faculty.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(faculty, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/faculties/{id}")
	public ResponseEntity<Faculty> getFacultyById(@PathVariable("id") long id) {
		Optional<Faculty> facultyData = facultyRepository.findById(id);

		if (facultyData.isPresent()) {
			return new ResponseEntity<>(facultyData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/////////////end delete update faculty data///////////////
	
	////////////////////////Saving Student Data ////////////////////////////////
	@PostMapping("/students")
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		try {
			Student _student = studentRepository
					.save(new Student(student.getName(), student.getCourse(), false));
			return new ResponseEntity<>(_student, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
		
	//////////////////////////END student Data ///////////////////////////////
	
////////////////////////Saving Faculty Data ////////////////////////////////
@PostMapping("/faculties")
public ResponseEntity<Faculty> createfaculty(@RequestBody Faculty faculty) {
try {
Faculty _faculty = facultyRepository
.save(new Faculty(faculty.getFacultyname(), faculty.getSubject(), false));
return new ResponseEntity<>(_faculty, HttpStatus.CREATED);
} catch (Exception e) {
return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
}
}


//////////////////////////END Faculty Data ///////////////////////////////
//////////////////////////START SUBJECT Data ///////////////////////////////
@PostMapping("/subject")
public ResponseEntity<Subject> createsubject(@RequestBody Subject subject) {
try {
Subject _subject = subjectRepository
.save(new Subject(subject.getName(), subject.getTime(), false));
return new ResponseEntity<>(_subject, HttpStatus.CREATED);
} catch (Exception e) {
return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
}
}
@PutMapping("/subject{id}")
public ResponseEntity<Subject> updateSubject(@PathVariable("id") long id, @RequestBody Subject subject) {
	Optional<Subject> subjectData = subjectRepository.findById(id);

	if (subjectData.isPresent()) {
		Subject _subject = subjectData.get();
		_subject.setName(subject.getName());
		_subject.setTime(subject.getTime());
		_subject.setClsBreak(subject.isClsBreak());
		return new ResponseEntity<>(subjectRepository.save(_subject), HttpStatus.OK);
	} else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}


@DeleteMapping("/subject/{id}")
public ResponseEntity<HttpStatus> deleteSubject(@PathVariable("id") long id) {
	try {
		subjectRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	} catch (Exception e) {
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

@DeleteMapping("/subject")
public ResponseEntity<HttpStatus> deleteAllsubject() {
	try {
		subjectRepository.deleteAll();
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	} catch (Exception e) {
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

@GetMapping("/subject/clsbreak")
public ResponseEntity<List<Subject>> findByClsBreak() {
	try {
		List<Subject> subject = subjectRepository.findByClsbreak(true);

		if (subject.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(subject, HttpStatus.OK);
	} catch (Exception e) {
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}@GetMapping("/subjects")
public ResponseEntity<List<Subject>> getAllSubject(@RequestParam(required = false) String name) {
	try {
		System.out.println("enter into get subject1");
		List<Subject> subjects = new ArrayList<Subject>();
		System.out.println("enter into get subject2");
		if (name==null)
		{
			System.out.println("if method"+subjects.size());
			subjectRepository.findAll().forEach(subjects::add);
			
		}else
		{
			System.out.println("else method"+subjects.size());
			subjectRepository.findByNameContaining(name).forEach(subjects::add);
			
		}

		if(subjects.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		System.out.println("enter into get subject3");
		return new ResponseEntity<>(subjects, HttpStatus.OK);
	} catch (Exception e) {
		System.out.println("exception"+e);
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
@GetMapping("/subject/{id}")
public ResponseEntity<Subject> getSubjectById(@PathVariable("id") long id) {
	Optional<Subject> subjectData = subjectRepository.findById(id);

	if (subjectData.isPresent()) {
		return new ResponseEntity<>(subjectData.get(), HttpStatus.OK);
	} else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}

//////////////////////////END SUBJECT Data ///////////////////////////////


@PutMapping("/tutorials/{id}")
	public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
		Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

		if (tutorialData.isPresent()) {
			Tutorial _tutorial = tutorialData.get();
			_tutorial.setTitle(tutorial.getTitle());
			_tutorial.setDescription(tutorial.getDescription());
			_tutorial.setPublished(tutorial.isPublished());
			return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/tutorials/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		try {
			tutorialRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/tutorials")
	public ResponseEntity<HttpStatus> deleteAllTutorials() {
		try {
			tutorialRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/tutorials/published")
	public ResponseEntity<List<Tutorial>> findByPublished() {
		try {
			List<Tutorial> tutorials = tutorialRepository.findByPublished(true);

			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
