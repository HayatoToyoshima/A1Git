package com.example.demo;



import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public String getAllBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        model.addAttribute("book", new Book());
        return "books";
    }

    @PostMapping
    public String createBook(@ModelAttribute Book book) {
        bookService.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable("id") Long id, Model model) {
        Book book = bookService.getBookById(id).orElse(new Book());
        model.addAttribute("book", book);
        return "books";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable("id") Long id, @ModelAttribute Book bookDetails) {
        bookService.getBookById(id).ifPresent(book -> {
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            book.setIsbn(bookDetails.getIsbn());
            bookService.saveBook(book);
        });
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return "redirect:/books";
    }

    @GetMapping("/clock")
    public String getClock() {
    	return "clock";
    }
//------------------------------------------------------------------------------------------
    @GetMapping("/ex/month")
    public String month(Model model) {
    	Integer rand=(int)(12*Math.random()+1);
    	model.addAttribute("month",rand);
		return "month";
    	
    }
//------------------------------------------------------------------------------------------
    @GetMapping("/ex/inline")
    public String index(Model model) {
        int[][][] multiArray = new int[10][10][10];
        Random random = new Random();

        // 10x10x10の多次元配列にランダムな数値を埋める
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    multiArray[i][j][k] = random.nextInt(100); // 0から99までのランダムな数値
                }
            }
        }

        model.addAttribute("four", "four");
        Integer rand=(int)(10*Math.random()+1);
        if(rand%2==0) {
            model.addAttribute("rand_flg",true);        	
        }
        else {
            model.addAttribute("rand_flg",false);            	
        }
        

        model.addAttribute("multiArray", multiArray);
        
        return "inline";
    }
  //------------------------------------------------------------------------------------------   
    
    
}
