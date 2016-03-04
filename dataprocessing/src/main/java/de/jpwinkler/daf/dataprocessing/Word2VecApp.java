package de.jpwinkler.daf.dataprocessing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.embeddings.wordvectors.WordVectors;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.deeplearning4j.text.sentenceiterator.SentencePreProcessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.dataprocessing.streaming.SimpleFolderCSVSpliterator;
import de.jpwinkler.daf.dataprocessing.utils.DoorsObjectSentenceIterator;

public class Word2VecApp {

    public static void main(final String[] args) throws IOException {
        // buildWord2VecModel();
        final WordVectors vec = WordVectorSerializer.loadTxtVectors(new File("words.txt"));
        testWord2VecModel(vec, "dauerhaft");
        testWord2VecModel(vec, "neue");
        testWord2VecModel(vec, "laufende");
        testWord2VecModel(vec, "Volt");
        testWord2VecModel(vec, "volt");
        testWord2VecModel(vec, "V");
    }

    private static Iterator<DoorsObject> getIterator() {
        return StreamSupport.stream(new SimpleFolderCSVSpliterator(new File("C:/WORK/ml-tasks/reqinf-comp"), false), false).iterator();
    }

    public static void buildWord2VecModel() throws IOException {

        final SentenceIterator i = new DoorsObjectSentenceIterator(() -> getIterator());
        i.setPreProcessor(new SentencePreProcessor() {

            private static final long serialVersionUID = 1L;

            @Override
            public String preProcess(final String sentence) {
                return sentence.toLowerCase();
            }

        });

        final TokenizerFactory tokenizer = new DefaultTokenizerFactory();

        final int batchSize = 1000;
        final int iterations = 3;
        final int layerSize = 500;

        final Word2Vec vec = new Word2Vec.Builder()
                .batchSize(batchSize) // # words per minibatch.
                .minWordFrequency(5) //
                .useAdaGrad(false) //
                .layerSize(layerSize) // word feature vector size
                .iterations(iterations) // # iterations to train
                .learningRate(0.025) //
                .minLearningRate(1e-3) // learning rate decays wrt # words.
                // floor learning
                .negativeSample(10) // sample size 10 words
                .iterate(i) //
                .tokenizerFactory(tokenizer)
                .build();

        final long t1 = System.currentTimeMillis();
        vec.fit();
        final long t2 = System.currentTimeMillis();

        System.out.println(t2 - t1);

        WordVectorSerializer.writeWordVectors(vec, "words.txt");

    }

    public static void testWord2VecModel(final WordVectors vec, final String word) throws FileNotFoundException {
        final Collection<String> words = vec.wordsNearest(word, 10);
        System.out.println("words nearest to '" + word + "': " + words);
    }
}
