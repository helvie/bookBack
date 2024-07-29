package com.bookApi.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lastname;
    private String firstname;
    private String mail;
    private String password;
    private String role;

    @OneToMany(mappedBy = "requester")
    private List<RequestedBook> requestedBooks;
    
    public User() {}

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<RequestedBook> getRequestedBooks() {
		return requestedBooks;
	}

	public void setRequestedBooks(List<RequestedBook> requestedBooks) {
		this.requestedBooks = requestedBooks;
	}

	public List<BookToLend> getBooksToLend() {
		return booksToLend;
	}

	public void setBooksToLend(List<BookToLend> booksToLend) {
		this.booksToLend = booksToLend;
	}

	public List<Loan> getLoansAsBorrower() {
		return loansAsBorrower;
	}

	public void setLoansAsBorrower(List<Loan> loansAsBorrower) {
		this.loansAsBorrower = loansAsBorrower;
	}

	public List<Loan> getLoansAsLender() {
		return loansAsLender;
	}

	public void setLoansAsLender(List<Loan> loansAsLender) {
		this.loansAsLender = loansAsLender;
	}

	public List<Message> getSentMessages() {
		return sentMessages;
	}

	public void setSentMessages(List<Message> sentMessages) {
		this.sentMessages = sentMessages;
	}

	public List<Message> getReceivedMessages() {
		return receivedMessages;
	}

	public void setReceivedMessages(List<Message> receivedMessages) {
		this.receivedMessages = receivedMessages;
	}

	@OneToMany(mappedBy = "owner")
    private List<BookToLend> booksToLend;

    @OneToMany(mappedBy = "borrower")
    private List<Loan> loansAsBorrower;

    @OneToMany(mappedBy = "lender")
    private List<Loan> loansAsLender;

    @OneToMany(mappedBy = "sender")
    private List<Message> sentMessages;

    @OneToMany(mappedBy = "receiver")
    private List<Message> receivedMessages;

}