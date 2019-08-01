package com.example.machine_larning_sample;

import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

/**
 * StanfordCoreNLP Sample
 * ※ 英語リソースのみ形態素解析可能
 */
public class App {
	
    public static void main( String[] args ) {
    	
    	// 形態素解析対象テキスト設定
    	StringBuilder target = new StringBuilder();
		target.append("I ask everyone to not rush to judgment and allow the investigation to be completed,")
				.append(" Orange County Sheriff Jerry Demings said at a news conference in Orlando.");

		// 形態素解析プロパティ設定
		Properties properties = new Properties();
		properties.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner");
		StanfordCoreNLP coreNLP = new StanfordCoreNLP(properties);
		Annotation annotation = new Annotation(target.toString());
		coreNLP.annotate(annotation);
		
		/*
		 * 形態素解析実行
		 * 品詞一覧: https://en.wikipedia.org/wiki/Brown_Corpus
		 */
		List<CoreLabel> labels = annotation.get(TokensAnnotation.class);
		labels.stream().forEach(cl -> System.out.println(
				"文字列: " + cl.get(TextAnnotation.class) + ", "				// 文字列
				+ "品詞: " + cl.get(PartOfSpeechAnnotation.class) + ", "		// 品詞
				+ "現在形: " + cl.get(LemmaAnnotation.class) + ", "			// 現在形
				+ "固有表現: " + cl.get(NamedEntityTagAnnotation.class)));	// 固有表現
		
    }

}
