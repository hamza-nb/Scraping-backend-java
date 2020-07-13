package com.ml_sma;

import com.ml_sma.DAO.StatisticRepository;
import com.ml_sma.DAO.TweetsRepository;
import com.ml_sma.entity.AlgoPredicted;
import com.ml_sma.entity.Sentiment;
import com.ml_sma.entity.Statistic;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MlSmaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MlSmaApplication.class, args);
	}
	@Bean
	CommandLineRunner start(TweetsRepository repository) {
		return args -> {
			repository.deleteAll();

			};
	}

}
