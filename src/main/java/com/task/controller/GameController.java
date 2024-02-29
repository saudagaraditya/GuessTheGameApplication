package com.task.controller;

import java.util.Random;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameController {

	private final int MAX_NUMBER = 100;
	private final int MAX_ATTEMPTS = 5;
	private int generatedNumber;
	private int remainingAttempts;
	private int totalScore = 0;

	@GetMapping("/start")
	public String startGame() {
		generatedNumber = new Random().nextInt(MAX_NUMBER) + 1;
		remainingAttempts = MAX_ATTEMPTS;
		totalScore = 0;
		return "Welcome to Guess the Number game! \nGuess a number between 1 and " + MAX_NUMBER + generatedNumber;
	}

	@PostMapping("/guess")
	public String guessNumber(@RequestParam int guess) {
		remainingAttempts--;

		if (guess == generatedNumber) {
			int score = remainingAttempts + 1;
			totalScore += score;
			return "Congratulations! You guessed the correct number: " + generatedNumber + "\nTotal score: "
					+ totalScore + "\nGame over! Please start a new round.";
		} else if (guess < generatedNumber) {
			if (remainingAttempts <= 0) {
				return "Game Over! The correct number was: " + generatedNumber
						+ "\nNo attempts left. Please start a new round.";
			}
			return "Try again! The number is higher. Attempts left: " + remainingAttempts;
		} else {

			return "Try again! The number is lower. Attempts left: " + remainingAttempts;
		}
	}

}
