package de.jpwinkler.daf.dafimpl.dbtasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.doorsdb.tasks.ModuleTaskBuilder;
import de.jpwinkler.daf.doorsdb.tasks.ObjectCSVPass;

public class MakeLuceneIndexTask {

    private static class Pass extends ObjectCSVPass {
        private final IndexWriter writer;
        private final Directory index;

        public Pass() throws IOException {
            final StandardAnalyzer analyzer = new StandardAnalyzer();
            index = FSDirectory.open(new File("index").toPath());
            final IndexWriterConfig config = new IndexWriterConfig(analyzer);
            writer = new IndexWriter(index, config);
        }

        @Override
        protected void processObject(final DoorsObject object) {
            final Document doc = new Document();
            doc.add(new TextField("text", object.getText(), Field.Store.YES));
            final String source = getModule().getFullName() + ":" + object.getObjectIdentifier();
            doc.add(new StringField("source", source, Field.Store.YES));
            final String ot = object.getAttributes().get("Object Type");
            if (ot != null && !ot.isEmpty()) {
                doc.add(new StringField("type", ot, Field.Store.YES));
            }
            try {
                writer.addDocument(doc);
            } catch (final IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    public static void main(final String[] args) throws FileNotFoundException, IOException {
        new ModuleTaskBuilder().withPass(new Pass()).buildAndRun();
    }

}
