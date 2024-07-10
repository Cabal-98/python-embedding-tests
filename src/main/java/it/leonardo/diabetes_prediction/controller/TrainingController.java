package it.leonardo.diabetes_prediction.controller;

import ai.djl.Model;
import ai.djl.training.Trainer;
import ai.djl.translate.TranslateException;
import it.leonardo.diabetes_prediction.ai.DefineModel;
import it.leonardo.diabetes_prediction.ai.TrainModel;
import it.leonardo.diabetes_prediction.configuration.AppConfiguration;
import it.leonardo.diabetes_prediction.service.DataAnalisiService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Path;

@RestController
@RequestMapping("/model")
@AllArgsConstructor
public class TrainingController {

    private AppConfiguration appConfiguration;
    private DataAnalisiService dataAnalisiService;

    @GetMapping(path = "/training")
    public String modelTraining() throws TranslateException, IOException {

        double[][] data =dataAnalisiService.analisiDatiBalanced();

        Model modello = DefineModel.createModel();
        Trainer trainer = TrainModel.createTrainer(modello, data);
        TrainModel.train(trainer, data);

        return """
                Addestramento Completato
                """;
    }


}
