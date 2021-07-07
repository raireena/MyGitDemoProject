package com.example.document.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.example.document.entity.Document;
import com.example.document.repository.DocumentRepo;

@Controller
public class DocumentController {

	@Autowired
	DocumentRepo documentRepo;

	@RequestMapping("/displayDocument")
	public String displayDocument(ModelMap modelMap) {
		getListOfDocuments(modelMap);

		return "displayDocument";
	}

	private void getListOfDocuments(ModelMap modelMap) {
		List<Document> documents = documentRepo.findAll();

		modelMap.addAttribute("documents", documents);
	}

	@RequestMapping(value = "/uploadDocument", method = RequestMethod.POST)
	public String uploadDocument(@RequestParam("id") Long id, @RequestParam("document") MultipartFile file,
			ModelMap modelMap) {
		Document document = new Document();
		document.setId(id);
		document.setName(file.getOriginalFilename());

		try {
			document.setData(file.getBytes());
		} catch (IOException e) {

			e.printStackTrace();
		}

		documentRepo.save(document);

		getListOfDocuments(modelMap);

		return "displayDocument";

	}

	@RequestMapping("/downloadDocument")
	public StreamingResponseBody downloadDocument(@RequestParam("id") Long id, HttpServletResponse response) {

		Document document = documentRepo.getById(id);

		byte[] a = document.getData();

		response.setHeader("content-Disposition", "attachment;");

		return outputStream -> {
			outputStream.write(a);
		};

	}

}
