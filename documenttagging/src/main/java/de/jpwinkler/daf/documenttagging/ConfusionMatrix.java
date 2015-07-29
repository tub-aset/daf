package de.jpwinkler.daf.documenttagging;

import java.util.ArrayList;
import java.util.List;

public class ConfusionMatrix<TAG> {


    private int[][] matrix;
	private List<TAG> tags;
	
	private int sumRow(int row) {
		int result = 0;
		for (int i = 0; i < tags.size(); i++) {
			result += matrix[i][row];
		}
		return result;
	}
	
	private int sumColumn(int column) {
		int result = 0;
		for (int i = 0; i < tags.size(); i++) {
			result += matrix[column][i];
		}
		return result;
	}

    public <X> ConfusionMatrix(final TaggedDocument<X, TAG> taggedDocument) {
    	tags = new ArrayList<>(taggedDocument.getTags());
    	matrix = new int[tags.size()][tags.size()];
    	
        for (final X e : taggedDocument.getElements()) {
            final TAG predicted = (TAG) taggedDocument.getPredictedTag(e);
            final TAG actual = (TAG) taggedDocument.getActualTag(e);
            if (predicted != null && actual != null) {
                matrix[tags.indexOf(predicted)][tags.indexOf(actual)]++;
            } else {
                System.out.println("oops...");
            }
        }
        
    }

    public int get(final TAG predicted, final TAG actual) {
    	return  matrix[tags.indexOf(predicted)][tags.indexOf(actual)];
    }
    
    public float getPrecision(TAG tag) {
    	int tagIndex = tags.indexOf(tag);
    	return (float) matrix[tagIndex][tagIndex] / (float) sumColumn(tagIndex);
    }
    
    public float getRecall(TAG tag) {
    	int tagIndex = tags.indexOf(tag);
    	return (float) matrix[tagIndex][tagIndex] / (float) sumRow(tagIndex);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("\t");
        for (final TAG headerTag : tags) {
            builder.append(headerTag.toString());
            builder.append("\t");
        }
        builder.append("\n");
        for (final TAG actual : tags) {
            builder.append(actual.toString());
            builder.append("\t");
            for (final TAG predicted : tags) {
                builder.append(get(predicted, actual));
                builder.append("\t");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

}
