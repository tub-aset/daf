package de.jpwinkler.daf.workflowdsl.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import de.jpwinkler.daf.workflowdsl.services.WorkflowDslGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalWorkflowDslParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_STRING", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'target'", "'moduleset'", "'{'", "'}'", "'csv'", "'doors'", "'doorsurl'", "'csvfolder'", "'var'", "'='", "'[]'", "','", "'constructor'", "'op'", "'source'", "'for'", "'in'", "'dependency'", "'implementation'"
    };
    public static final int RULE_STRING=5;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__12=12;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int EOF=-1;
    public static final int RULE_ID=4;
    public static final int RULE_WS=9;
    public static final int RULE_ANY_OTHER=10;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=6;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;

    // delegates
    // delegators


        public InternalWorkflowDslParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalWorkflowDslParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalWorkflowDslParser.tokenNames; }
    public String getGrammarFileName() { return "../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g"; }



     	private WorkflowDslGrammarAccess grammarAccess;
     	
        public InternalWorkflowDslParser(TokenStream input, WorkflowDslGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "WorkflowModel";	
       	}
       	
       	@Override
       	protected WorkflowDslGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start "entryRuleWorkflowModel"
    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:67:1: entryRuleWorkflowModel returns [EObject current=null] : iv_ruleWorkflowModel= ruleWorkflowModel EOF ;
    public final EObject entryRuleWorkflowModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWorkflowModel = null;


        try {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:68:2: (iv_ruleWorkflowModel= ruleWorkflowModel EOF )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:69:2: iv_ruleWorkflowModel= ruleWorkflowModel EOF
            {
             newCompositeNode(grammarAccess.getWorkflowModelRule()); 
            pushFollow(FOLLOW_ruleWorkflowModel_in_entryRuleWorkflowModel75);
            iv_ruleWorkflowModel=ruleWorkflowModel();

            state._fsp--;

             current =iv_ruleWorkflowModel; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleWorkflowModel85); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleWorkflowModel"


    // $ANTLR start "ruleWorkflowModel"
    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:76:1: ruleWorkflowModel returns [EObject current=null] : ( (lv_elements_0_0= ruleWorkflowElement ) )* ;
    public final EObject ruleWorkflowModel() throws RecognitionException {
        EObject current = null;

        EObject lv_elements_0_0 = null;


         enterRule(); 
            
        try {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:79:28: ( ( (lv_elements_0_0= ruleWorkflowElement ) )* )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:80:1: ( (lv_elements_0_0= ruleWorkflowElement ) )*
            {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:80:1: ( (lv_elements_0_0= ruleWorkflowElement ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>=11 && LA1_0<=12)||LA1_0==19||(LA1_0>=23 && LA1_0<=24)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:81:1: (lv_elements_0_0= ruleWorkflowElement )
            	    {
            	    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:81:1: (lv_elements_0_0= ruleWorkflowElement )
            	    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:82:3: lv_elements_0_0= ruleWorkflowElement
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getWorkflowModelAccess().getElementsWorkflowElementParserRuleCall_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleWorkflowElement_in_ruleWorkflowModel130);
            	    lv_elements_0_0=ruleWorkflowElement();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getWorkflowModelRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"elements",
            	            		lv_elements_0_0, 
            	            		"WorkflowElement");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleWorkflowModel"


    // $ANTLR start "entryRuleWorkflowElement"
    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:106:1: entryRuleWorkflowElement returns [EObject current=null] : iv_ruleWorkflowElement= ruleWorkflowElement EOF ;
    public final EObject entryRuleWorkflowElement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWorkflowElement = null;


        try {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:107:2: (iv_ruleWorkflowElement= ruleWorkflowElement EOF )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:108:2: iv_ruleWorkflowElement= ruleWorkflowElement EOF
            {
             newCompositeNode(grammarAccess.getWorkflowElementRule()); 
            pushFollow(FOLLOW_ruleWorkflowElement_in_entryRuleWorkflowElement166);
            iv_ruleWorkflowElement=ruleWorkflowElement();

            state._fsp--;

             current =iv_ruleWorkflowElement; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleWorkflowElement176); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleWorkflowElement"


    // $ANTLR start "ruleWorkflowElement"
    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:115:1: ruleWorkflowElement returns [EObject current=null] : (this_Target_0= ruleTarget | this_Step_1= ruleStep | this_ModuleSet_2= ruleModuleSet | this_Variable_3= ruleVariable ) ;
    public final EObject ruleWorkflowElement() throws RecognitionException {
        EObject current = null;

        EObject this_Target_0 = null;

        EObject this_Step_1 = null;

        EObject this_ModuleSet_2 = null;

        EObject this_Variable_3 = null;


         enterRule(); 
            
        try {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:118:28: ( (this_Target_0= ruleTarget | this_Step_1= ruleStep | this_ModuleSet_2= ruleModuleSet | this_Variable_3= ruleVariable ) )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:119:1: (this_Target_0= ruleTarget | this_Step_1= ruleStep | this_ModuleSet_2= ruleModuleSet | this_Variable_3= ruleVariable )
            {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:119:1: (this_Target_0= ruleTarget | this_Step_1= ruleStep | this_ModuleSet_2= ruleModuleSet | this_Variable_3= ruleVariable )
            int alt2=4;
            switch ( input.LA(1) ) {
            case 11:
                {
                alt2=1;
                }
                break;
            case 23:
            case 24:
                {
                alt2=2;
                }
                break;
            case 12:
                {
                alt2=3;
                }
                break;
            case 19:
                {
                alt2=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:120:5: this_Target_0= ruleTarget
                    {
                     
                            newCompositeNode(grammarAccess.getWorkflowElementAccess().getTargetParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleTarget_in_ruleWorkflowElement223);
                    this_Target_0=ruleTarget();

                    state._fsp--;

                     
                            current = this_Target_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:130:5: this_Step_1= ruleStep
                    {
                     
                            newCompositeNode(grammarAccess.getWorkflowElementAccess().getStepParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_ruleStep_in_ruleWorkflowElement250);
                    this_Step_1=ruleStep();

                    state._fsp--;

                     
                            current = this_Step_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:140:5: this_ModuleSet_2= ruleModuleSet
                    {
                     
                            newCompositeNode(grammarAccess.getWorkflowElementAccess().getModuleSetParserRuleCall_2()); 
                        
                    pushFollow(FOLLOW_ruleModuleSet_in_ruleWorkflowElement277);
                    this_ModuleSet_2=ruleModuleSet();

                    state._fsp--;

                     
                            current = this_ModuleSet_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:150:5: this_Variable_3= ruleVariable
                    {
                     
                            newCompositeNode(grammarAccess.getWorkflowElementAccess().getVariableParserRuleCall_3()); 
                        
                    pushFollow(FOLLOW_ruleVariable_in_ruleWorkflowElement304);
                    this_Variable_3=ruleVariable();

                    state._fsp--;

                     
                            current = this_Variable_3; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleWorkflowElement"


    // $ANTLR start "entryRuleTarget"
    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:166:1: entryRuleTarget returns [EObject current=null] : iv_ruleTarget= ruleTarget EOF ;
    public final EObject entryRuleTarget() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTarget = null;


        try {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:167:2: (iv_ruleTarget= ruleTarget EOF )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:168:2: iv_ruleTarget= ruleTarget EOF
            {
             newCompositeNode(grammarAccess.getTargetRule()); 
            pushFollow(FOLLOW_ruleTarget_in_entryRuleTarget339);
            iv_ruleTarget=ruleTarget();

            state._fsp--;

             current =iv_ruleTarget; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTarget349); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTarget"


    // $ANTLR start "ruleTarget"
    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:175:1: ruleTarget returns [EObject current=null] : (otherlv_0= 'target' ( (otherlv_1= RULE_ID ) ) ) ;
    public final EObject ruleTarget() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:178:28: ( (otherlv_0= 'target' ( (otherlv_1= RULE_ID ) ) ) )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:179:1: (otherlv_0= 'target' ( (otherlv_1= RULE_ID ) ) )
            {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:179:1: (otherlv_0= 'target' ( (otherlv_1= RULE_ID ) ) )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:179:3: otherlv_0= 'target' ( (otherlv_1= RULE_ID ) )
            {
            otherlv_0=(Token)match(input,11,FOLLOW_11_in_ruleTarget386); 

                	newLeafNode(otherlv_0, grammarAccess.getTargetAccess().getTargetKeyword_0());
                
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:183:1: ( (otherlv_1= RULE_ID ) )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:184:1: (otherlv_1= RULE_ID )
            {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:184:1: (otherlv_1= RULE_ID )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:185:3: otherlv_1= RULE_ID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getTargetRule());
            	        }
                    
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleTarget406); 

            		newLeafNode(otherlv_1, grammarAccess.getTargetAccess().getStepStepCrossReference_1_0()); 
            	

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTarget"


    // $ANTLR start "entryRuleModuleSet"
    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:204:1: entryRuleModuleSet returns [EObject current=null] : iv_ruleModuleSet= ruleModuleSet EOF ;
    public final EObject entryRuleModuleSet() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModuleSet = null;


        try {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:205:2: (iv_ruleModuleSet= ruleModuleSet EOF )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:206:2: iv_ruleModuleSet= ruleModuleSet EOF
            {
             newCompositeNode(grammarAccess.getModuleSetRule()); 
            pushFollow(FOLLOW_ruleModuleSet_in_entryRuleModuleSet442);
            iv_ruleModuleSet=ruleModuleSet();

            state._fsp--;

             current =iv_ruleModuleSet; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleModuleSet452); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleModuleSet"


    // $ANTLR start "ruleModuleSet"
    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:213:1: ruleModuleSet returns [EObject current=null] : (otherlv_0= 'moduleset' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_moduleSetEntries_3_0= ruleModuleSetEntry ) )* otherlv_4= '}' ) ;
    public final EObject ruleModuleSet() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_moduleSetEntries_3_0 = null;


         enterRule(); 
            
        try {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:216:28: ( (otherlv_0= 'moduleset' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_moduleSetEntries_3_0= ruleModuleSetEntry ) )* otherlv_4= '}' ) )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:217:1: (otherlv_0= 'moduleset' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_moduleSetEntries_3_0= ruleModuleSetEntry ) )* otherlv_4= '}' )
            {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:217:1: (otherlv_0= 'moduleset' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_moduleSetEntries_3_0= ruleModuleSetEntry ) )* otherlv_4= '}' )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:217:3: otherlv_0= 'moduleset' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_moduleSetEntries_3_0= ruleModuleSetEntry ) )* otherlv_4= '}'
            {
            otherlv_0=(Token)match(input,12,FOLLOW_12_in_ruleModuleSet489); 

                	newLeafNode(otherlv_0, grammarAccess.getModuleSetAccess().getModulesetKeyword_0());
                
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:221:1: ( (lv_name_1_0= RULE_ID ) )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:222:1: (lv_name_1_0= RULE_ID )
            {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:222:1: (lv_name_1_0= RULE_ID )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:223:3: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleModuleSet506); 

            			newLeafNode(lv_name_1_0, grammarAccess.getModuleSetAccess().getNameIDTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getModuleSetRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_1_0, 
                    		"ID");
            	    

            }


            }

            otherlv_2=(Token)match(input,13,FOLLOW_13_in_ruleModuleSet523); 

                	newLeafNode(otherlv_2, grammarAccess.getModuleSetAccess().getLeftCurlyBracketKeyword_2());
                
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:243:1: ( (lv_moduleSetEntries_3_0= ruleModuleSetEntry ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>=15 && LA3_0<=18)) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:244:1: (lv_moduleSetEntries_3_0= ruleModuleSetEntry )
            	    {
            	    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:244:1: (lv_moduleSetEntries_3_0= ruleModuleSetEntry )
            	    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:245:3: lv_moduleSetEntries_3_0= ruleModuleSetEntry
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getModuleSetAccess().getModuleSetEntriesModuleSetEntryParserRuleCall_3_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleModuleSetEntry_in_ruleModuleSet544);
            	    lv_moduleSetEntries_3_0=ruleModuleSetEntry();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getModuleSetRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"moduleSetEntries",
            	            		lv_moduleSetEntries_3_0, 
            	            		"ModuleSetEntry");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            otherlv_4=(Token)match(input,14,FOLLOW_14_in_ruleModuleSet557); 

                	newLeafNode(otherlv_4, grammarAccess.getModuleSetAccess().getRightCurlyBracketKeyword_4());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleModuleSet"


    // $ANTLR start "entryRuleMODULE_SET_ENTRY_TYPE"
    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:273:1: entryRuleMODULE_SET_ENTRY_TYPE returns [String current=null] : iv_ruleMODULE_SET_ENTRY_TYPE= ruleMODULE_SET_ENTRY_TYPE EOF ;
    public final String entryRuleMODULE_SET_ENTRY_TYPE() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleMODULE_SET_ENTRY_TYPE = null;


        try {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:274:2: (iv_ruleMODULE_SET_ENTRY_TYPE= ruleMODULE_SET_ENTRY_TYPE EOF )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:275:2: iv_ruleMODULE_SET_ENTRY_TYPE= ruleMODULE_SET_ENTRY_TYPE EOF
            {
             newCompositeNode(grammarAccess.getMODULE_SET_ENTRY_TYPERule()); 
            pushFollow(FOLLOW_ruleMODULE_SET_ENTRY_TYPE_in_entryRuleMODULE_SET_ENTRY_TYPE594);
            iv_ruleMODULE_SET_ENTRY_TYPE=ruleMODULE_SET_ENTRY_TYPE();

            state._fsp--;

             current =iv_ruleMODULE_SET_ENTRY_TYPE.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleMODULE_SET_ENTRY_TYPE605); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMODULE_SET_ENTRY_TYPE"


    // $ANTLR start "ruleMODULE_SET_ENTRY_TYPE"
    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:282:1: ruleMODULE_SET_ENTRY_TYPE returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'csv' | kw= 'doors' | kw= 'doorsurl' | kw= 'csvfolder' ) ;
    public final AntlrDatatypeRuleToken ruleMODULE_SET_ENTRY_TYPE() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:285:28: ( (kw= 'csv' | kw= 'doors' | kw= 'doorsurl' | kw= 'csvfolder' ) )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:286:1: (kw= 'csv' | kw= 'doors' | kw= 'doorsurl' | kw= 'csvfolder' )
            {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:286:1: (kw= 'csv' | kw= 'doors' | kw= 'doorsurl' | kw= 'csvfolder' )
            int alt4=4;
            switch ( input.LA(1) ) {
            case 15:
                {
                alt4=1;
                }
                break;
            case 16:
                {
                alt4=2;
                }
                break;
            case 17:
                {
                alt4=3;
                }
                break;
            case 18:
                {
                alt4=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:287:2: kw= 'csv'
                    {
                    kw=(Token)match(input,15,FOLLOW_15_in_ruleMODULE_SET_ENTRY_TYPE643); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getMODULE_SET_ENTRY_TYPEAccess().getCsvKeyword_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:294:2: kw= 'doors'
                    {
                    kw=(Token)match(input,16,FOLLOW_16_in_ruleMODULE_SET_ENTRY_TYPE662); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getMODULE_SET_ENTRY_TYPEAccess().getDoorsKeyword_1()); 
                        

                    }
                    break;
                case 3 :
                    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:301:2: kw= 'doorsurl'
                    {
                    kw=(Token)match(input,17,FOLLOW_17_in_ruleMODULE_SET_ENTRY_TYPE681); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getMODULE_SET_ENTRY_TYPEAccess().getDoorsurlKeyword_2()); 
                        

                    }
                    break;
                case 4 :
                    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:308:2: kw= 'csvfolder'
                    {
                    kw=(Token)match(input,18,FOLLOW_18_in_ruleMODULE_SET_ENTRY_TYPE700); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getMODULE_SET_ENTRY_TYPEAccess().getCsvfolderKeyword_3()); 
                        

                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMODULE_SET_ENTRY_TYPE"


    // $ANTLR start "entryRuleModuleSetEntry"
    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:321:1: entryRuleModuleSetEntry returns [EObject current=null] : iv_ruleModuleSetEntry= ruleModuleSetEntry EOF ;
    public final EObject entryRuleModuleSetEntry() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModuleSetEntry = null;


        try {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:322:2: (iv_ruleModuleSetEntry= ruleModuleSetEntry EOF )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:323:2: iv_ruleModuleSetEntry= ruleModuleSetEntry EOF
            {
             newCompositeNode(grammarAccess.getModuleSetEntryRule()); 
            pushFollow(FOLLOW_ruleModuleSetEntry_in_entryRuleModuleSetEntry740);
            iv_ruleModuleSetEntry=ruleModuleSetEntry();

            state._fsp--;

             current =iv_ruleModuleSetEntry; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleModuleSetEntry750); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleModuleSetEntry"


    // $ANTLR start "ruleModuleSetEntry"
    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:330:1: ruleModuleSetEntry returns [EObject current=null] : ( ( (lv_type_0_0= ruleMODULE_SET_ENTRY_TYPE ) ) ( (lv_reference_1_0= RULE_STRING ) ) ) ;
    public final EObject ruleModuleSetEntry() throws RecognitionException {
        EObject current = null;

        Token lv_reference_1_0=null;
        AntlrDatatypeRuleToken lv_type_0_0 = null;


         enterRule(); 
            
        try {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:333:28: ( ( ( (lv_type_0_0= ruleMODULE_SET_ENTRY_TYPE ) ) ( (lv_reference_1_0= RULE_STRING ) ) ) )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:334:1: ( ( (lv_type_0_0= ruleMODULE_SET_ENTRY_TYPE ) ) ( (lv_reference_1_0= RULE_STRING ) ) )
            {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:334:1: ( ( (lv_type_0_0= ruleMODULE_SET_ENTRY_TYPE ) ) ( (lv_reference_1_0= RULE_STRING ) ) )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:334:2: ( (lv_type_0_0= ruleMODULE_SET_ENTRY_TYPE ) ) ( (lv_reference_1_0= RULE_STRING ) )
            {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:334:2: ( (lv_type_0_0= ruleMODULE_SET_ENTRY_TYPE ) )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:335:1: (lv_type_0_0= ruleMODULE_SET_ENTRY_TYPE )
            {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:335:1: (lv_type_0_0= ruleMODULE_SET_ENTRY_TYPE )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:336:3: lv_type_0_0= ruleMODULE_SET_ENTRY_TYPE
            {
             
            	        newCompositeNode(grammarAccess.getModuleSetEntryAccess().getTypeMODULE_SET_ENTRY_TYPEParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleMODULE_SET_ENTRY_TYPE_in_ruleModuleSetEntry796);
            lv_type_0_0=ruleMODULE_SET_ENTRY_TYPE();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getModuleSetEntryRule());
            	        }
                   		set(
                   			current, 
                   			"type",
                    		lv_type_0_0, 
                    		"MODULE_SET_ENTRY_TYPE");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:352:2: ( (lv_reference_1_0= RULE_STRING ) )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:353:1: (lv_reference_1_0= RULE_STRING )
            {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:353:1: (lv_reference_1_0= RULE_STRING )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:354:3: lv_reference_1_0= RULE_STRING
            {
            lv_reference_1_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleModuleSetEntry813); 

            			newLeafNode(lv_reference_1_0, grammarAccess.getModuleSetEntryAccess().getReferenceSTRINGTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getModuleSetEntryRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"reference",
                    		lv_reference_1_0, 
                    		"STRING");
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleModuleSetEntry"


    // $ANTLR start "entryRuleVariable"
    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:378:1: entryRuleVariable returns [EObject current=null] : iv_ruleVariable= ruleVariable EOF ;
    public final EObject entryRuleVariable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVariable = null;


        try {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:379:2: (iv_ruleVariable= ruleVariable EOF )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:380:2: iv_ruleVariable= ruleVariable EOF
            {
             newCompositeNode(grammarAccess.getVariableRule()); 
            pushFollow(FOLLOW_ruleVariable_in_entryRuleVariable854);
            iv_ruleVariable=ruleVariable();

            state._fsp--;

             current =iv_ruleVariable; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleVariable864); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVariable"


    // $ANTLR start "ruleVariable"
    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:387:1: ruleVariable returns [EObject current=null] : (this_SimpleVariable_0= ruleSimpleVariable | this_ArrayVariable_1= ruleArrayVariable ) ;
    public final EObject ruleVariable() throws RecognitionException {
        EObject current = null;

        EObject this_SimpleVariable_0 = null;

        EObject this_ArrayVariable_1 = null;


         enterRule(); 
            
        try {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:390:28: ( (this_SimpleVariable_0= ruleSimpleVariable | this_ArrayVariable_1= ruleArrayVariable ) )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:391:1: (this_SimpleVariable_0= ruleSimpleVariable | this_ArrayVariable_1= ruleArrayVariable )
            {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:391:1: (this_SimpleVariable_0= ruleSimpleVariable | this_ArrayVariable_1= ruleArrayVariable )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==19) ) {
                int LA5_1 = input.LA(2);

                if ( (LA5_1==RULE_ID) ) {
                    int LA5_2 = input.LA(3);

                    if ( (LA5_2==21) ) {
                        alt5=2;
                    }
                    else if ( (LA5_2==20) ) {
                        alt5=1;
                    }
                    else {
                        NoViableAltException nvae =
                            new NoViableAltException("", 5, 2, input);

                        throw nvae;
                    }
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 5, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:392:5: this_SimpleVariable_0= ruleSimpleVariable
                    {
                     
                            newCompositeNode(grammarAccess.getVariableAccess().getSimpleVariableParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleSimpleVariable_in_ruleVariable911);
                    this_SimpleVariable_0=ruleSimpleVariable();

                    state._fsp--;

                     
                            current = this_SimpleVariable_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:402:5: this_ArrayVariable_1= ruleArrayVariable
                    {
                     
                            newCompositeNode(grammarAccess.getVariableAccess().getArrayVariableParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_ruleArrayVariable_in_ruleVariable938);
                    this_ArrayVariable_1=ruleArrayVariable();

                    state._fsp--;

                     
                            current = this_ArrayVariable_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVariable"


    // $ANTLR start "entryRuleSimpleVariable"
    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:418:1: entryRuleSimpleVariable returns [EObject current=null] : iv_ruleSimpleVariable= ruleSimpleVariable EOF ;
    public final EObject entryRuleSimpleVariable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSimpleVariable = null;


        try {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:419:2: (iv_ruleSimpleVariable= ruleSimpleVariable EOF )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:420:2: iv_ruleSimpleVariable= ruleSimpleVariable EOF
            {
             newCompositeNode(grammarAccess.getSimpleVariableRule()); 
            pushFollow(FOLLOW_ruleSimpleVariable_in_entryRuleSimpleVariable973);
            iv_ruleSimpleVariable=ruleSimpleVariable();

            state._fsp--;

             current =iv_ruleSimpleVariable; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSimpleVariable983); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSimpleVariable"


    // $ANTLR start "ruleSimpleVariable"
    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:427:1: ruleSimpleVariable returns [EObject current=null] : (otherlv_0= 'var' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '=' ( (lv_value_3_0= RULE_STRING ) ) ) ;
    public final EObject ruleSimpleVariable() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token lv_value_3_0=null;

         enterRule(); 
            
        try {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:430:28: ( (otherlv_0= 'var' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '=' ( (lv_value_3_0= RULE_STRING ) ) ) )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:431:1: (otherlv_0= 'var' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '=' ( (lv_value_3_0= RULE_STRING ) ) )
            {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:431:1: (otherlv_0= 'var' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '=' ( (lv_value_3_0= RULE_STRING ) ) )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:431:3: otherlv_0= 'var' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '=' ( (lv_value_3_0= RULE_STRING ) )
            {
            otherlv_0=(Token)match(input,19,FOLLOW_19_in_ruleSimpleVariable1020); 

                	newLeafNode(otherlv_0, grammarAccess.getSimpleVariableAccess().getVarKeyword_0());
                
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:435:1: ( (lv_name_1_0= RULE_ID ) )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:436:1: (lv_name_1_0= RULE_ID )
            {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:436:1: (lv_name_1_0= RULE_ID )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:437:3: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleSimpleVariable1037); 

            			newLeafNode(lv_name_1_0, grammarAccess.getSimpleVariableAccess().getNameIDTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getSimpleVariableRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_1_0, 
                    		"ID");
            	    

            }


            }

            otherlv_2=(Token)match(input,20,FOLLOW_20_in_ruleSimpleVariable1054); 

                	newLeafNode(otherlv_2, grammarAccess.getSimpleVariableAccess().getEqualsSignKeyword_2());
                
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:457:1: ( (lv_value_3_0= RULE_STRING ) )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:458:1: (lv_value_3_0= RULE_STRING )
            {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:458:1: (lv_value_3_0= RULE_STRING )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:459:3: lv_value_3_0= RULE_STRING
            {
            lv_value_3_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleSimpleVariable1071); 

            			newLeafNode(lv_value_3_0, grammarAccess.getSimpleVariableAccess().getValueSTRINGTerminalRuleCall_3_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getSimpleVariableRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"value",
                    		lv_value_3_0, 
                    		"STRING");
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSimpleVariable"


    // $ANTLR start "entryRuleArrayVariable"
    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:483:1: entryRuleArrayVariable returns [EObject current=null] : iv_ruleArrayVariable= ruleArrayVariable EOF ;
    public final EObject entryRuleArrayVariable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleArrayVariable = null;


        try {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:484:2: (iv_ruleArrayVariable= ruleArrayVariable EOF )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:485:2: iv_ruleArrayVariable= ruleArrayVariable EOF
            {
             newCompositeNode(grammarAccess.getArrayVariableRule()); 
            pushFollow(FOLLOW_ruleArrayVariable_in_entryRuleArrayVariable1112);
            iv_ruleArrayVariable=ruleArrayVariable();

            state._fsp--;

             current =iv_ruleArrayVariable; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleArrayVariable1122); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleArrayVariable"


    // $ANTLR start "ruleArrayVariable"
    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:492:1: ruleArrayVariable returns [EObject current=null] : (otherlv_0= 'var' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '[]' otherlv_3= '=' otherlv_4= '{' ( ( (lv_items_5_0= RULE_STRING ) ) (otherlv_6= ',' ( (lv_items_7_0= RULE_STRING ) ) )* )? otherlv_8= '}' ) ;
    public final EObject ruleArrayVariable() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token lv_items_5_0=null;
        Token otherlv_6=null;
        Token lv_items_7_0=null;
        Token otherlv_8=null;

         enterRule(); 
            
        try {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:495:28: ( (otherlv_0= 'var' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '[]' otherlv_3= '=' otherlv_4= '{' ( ( (lv_items_5_0= RULE_STRING ) ) (otherlv_6= ',' ( (lv_items_7_0= RULE_STRING ) ) )* )? otherlv_8= '}' ) )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:496:1: (otherlv_0= 'var' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '[]' otherlv_3= '=' otherlv_4= '{' ( ( (lv_items_5_0= RULE_STRING ) ) (otherlv_6= ',' ( (lv_items_7_0= RULE_STRING ) ) )* )? otherlv_8= '}' )
            {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:496:1: (otherlv_0= 'var' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '[]' otherlv_3= '=' otherlv_4= '{' ( ( (lv_items_5_0= RULE_STRING ) ) (otherlv_6= ',' ( (lv_items_7_0= RULE_STRING ) ) )* )? otherlv_8= '}' )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:496:3: otherlv_0= 'var' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '[]' otherlv_3= '=' otherlv_4= '{' ( ( (lv_items_5_0= RULE_STRING ) ) (otherlv_6= ',' ( (lv_items_7_0= RULE_STRING ) ) )* )? otherlv_8= '}'
            {
            otherlv_0=(Token)match(input,19,FOLLOW_19_in_ruleArrayVariable1159); 

                	newLeafNode(otherlv_0, grammarAccess.getArrayVariableAccess().getVarKeyword_0());
                
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:500:1: ( (lv_name_1_0= RULE_ID ) )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:501:1: (lv_name_1_0= RULE_ID )
            {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:501:1: (lv_name_1_0= RULE_ID )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:502:3: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleArrayVariable1176); 

            			newLeafNode(lv_name_1_0, grammarAccess.getArrayVariableAccess().getNameIDTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getArrayVariableRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_1_0, 
                    		"ID");
            	    

            }


            }

            otherlv_2=(Token)match(input,21,FOLLOW_21_in_ruleArrayVariable1193); 

                	newLeafNode(otherlv_2, grammarAccess.getArrayVariableAccess().getLeftSquareBracketRightSquareBracketKeyword_2());
                
            otherlv_3=(Token)match(input,20,FOLLOW_20_in_ruleArrayVariable1205); 

                	newLeafNode(otherlv_3, grammarAccess.getArrayVariableAccess().getEqualsSignKeyword_3());
                
            otherlv_4=(Token)match(input,13,FOLLOW_13_in_ruleArrayVariable1217); 

                	newLeafNode(otherlv_4, grammarAccess.getArrayVariableAccess().getLeftCurlyBracketKeyword_4());
                
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:530:1: ( ( (lv_items_5_0= RULE_STRING ) ) (otherlv_6= ',' ( (lv_items_7_0= RULE_STRING ) ) )* )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==RULE_STRING) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:530:2: ( (lv_items_5_0= RULE_STRING ) ) (otherlv_6= ',' ( (lv_items_7_0= RULE_STRING ) ) )*
                    {
                    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:530:2: ( (lv_items_5_0= RULE_STRING ) )
                    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:531:1: (lv_items_5_0= RULE_STRING )
                    {
                    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:531:1: (lv_items_5_0= RULE_STRING )
                    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:532:3: lv_items_5_0= RULE_STRING
                    {
                    lv_items_5_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleArrayVariable1235); 

                    			newLeafNode(lv_items_5_0, grammarAccess.getArrayVariableAccess().getItemsSTRINGTerminalRuleCall_5_0_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getArrayVariableRule());
                    	        }
                           		addWithLastConsumed(
                           			current, 
                           			"items",
                            		lv_items_5_0, 
                            		"STRING");
                    	    

                    }


                    }

                    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:548:2: (otherlv_6= ',' ( (lv_items_7_0= RULE_STRING ) ) )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0==22) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:548:4: otherlv_6= ',' ( (lv_items_7_0= RULE_STRING ) )
                    	    {
                    	    otherlv_6=(Token)match(input,22,FOLLOW_22_in_ruleArrayVariable1253); 

                    	        	newLeafNode(otherlv_6, grammarAccess.getArrayVariableAccess().getCommaKeyword_5_1_0());
                    	        
                    	    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:552:1: ( (lv_items_7_0= RULE_STRING ) )
                    	    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:553:1: (lv_items_7_0= RULE_STRING )
                    	    {
                    	    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:553:1: (lv_items_7_0= RULE_STRING )
                    	    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:554:3: lv_items_7_0= RULE_STRING
                    	    {
                    	    lv_items_7_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleArrayVariable1270); 

                    	    			newLeafNode(lv_items_7_0, grammarAccess.getArrayVariableAccess().getItemsSTRINGTerminalRuleCall_5_1_1_0()); 
                    	    		

                    	    	        if (current==null) {
                    	    	            current = createModelElement(grammarAccess.getArrayVariableRule());
                    	    	        }
                    	           		addWithLastConsumed(
                    	           			current, 
                    	           			"items",
                    	            		lv_items_7_0, 
                    	            		"STRING");
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_8=(Token)match(input,14,FOLLOW_14_in_ruleArrayVariable1291); 

                	newLeafNode(otherlv_8, grammarAccess.getArrayVariableAccess().getRightCurlyBracketKeyword_6());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleArrayVariable"


    // $ANTLR start "entryRuleStep"
    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:582:1: entryRuleStep returns [EObject current=null] : iv_ruleStep= ruleStep EOF ;
    public final EObject entryRuleStep() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStep = null;


        try {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:583:2: (iv_ruleStep= ruleStep EOF )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:584:2: iv_ruleStep= ruleStep EOF
            {
             newCompositeNode(grammarAccess.getStepRule()); 
            pushFollow(FOLLOW_ruleStep_in_entryRuleStep1327);
            iv_ruleStep=ruleStep();

            state._fsp--;

             current =iv_ruleStep; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleStep1337); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleStep"


    // $ANTLR start "ruleStep"
    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:591:1: ruleStep returns [EObject current=null] : (this_ModelConstructorStep_0= ruleModelConstructorStep | this_ModelOperationStep_1= ruleModelOperationStep ) ;
    public final EObject ruleStep() throws RecognitionException {
        EObject current = null;

        EObject this_ModelConstructorStep_0 = null;

        EObject this_ModelOperationStep_1 = null;


         enterRule(); 
            
        try {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:594:28: ( (this_ModelConstructorStep_0= ruleModelConstructorStep | this_ModelOperationStep_1= ruleModelOperationStep ) )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:595:1: (this_ModelConstructorStep_0= ruleModelConstructorStep | this_ModelOperationStep_1= ruleModelOperationStep )
            {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:595:1: (this_ModelConstructorStep_0= ruleModelConstructorStep | this_ModelOperationStep_1= ruleModelOperationStep )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==23) ) {
                alt8=1;
            }
            else if ( (LA8_0==24) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:596:5: this_ModelConstructorStep_0= ruleModelConstructorStep
                    {
                     
                            newCompositeNode(grammarAccess.getStepAccess().getModelConstructorStepParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleModelConstructorStep_in_ruleStep1384);
                    this_ModelConstructorStep_0=ruleModelConstructorStep();

                    state._fsp--;

                     
                            current = this_ModelConstructorStep_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:606:5: this_ModelOperationStep_1= ruleModelOperationStep
                    {
                     
                            newCompositeNode(grammarAccess.getStepAccess().getModelOperationStepParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_ruleModelOperationStep_in_ruleStep1411);
                    this_ModelOperationStep_1=ruleModelOperationStep();

                    state._fsp--;

                     
                            current = this_ModelOperationStep_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleStep"


    // $ANTLR start "entryRuleModelConstructorStep"
    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:622:1: entryRuleModelConstructorStep returns [EObject current=null] : iv_ruleModelConstructorStep= ruleModelConstructorStep EOF ;
    public final EObject entryRuleModelConstructorStep() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModelConstructorStep = null;


        try {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:623:2: (iv_ruleModelConstructorStep= ruleModelConstructorStep EOF )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:624:2: iv_ruleModelConstructorStep= ruleModelConstructorStep EOF
            {
             newCompositeNode(grammarAccess.getModelConstructorStepRule()); 
            pushFollow(FOLLOW_ruleModelConstructorStep_in_entryRuleModelConstructorStep1446);
            iv_ruleModelConstructorStep=ruleModelConstructorStep();

            state._fsp--;

             current =iv_ruleModelConstructorStep; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleModelConstructorStep1456); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleModelConstructorStep"


    // $ANTLR start "ruleModelConstructorStep"
    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:631:1: ruleModelConstructorStep returns [EObject current=null] : (otherlv_0= 'constructor' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_features_3_0= ruleOperationFeature ) )* otherlv_4= '}' ) ;
    public final EObject ruleModelConstructorStep() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_features_3_0 = null;


         enterRule(); 
            
        try {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:634:28: ( (otherlv_0= 'constructor' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_features_3_0= ruleOperationFeature ) )* otherlv_4= '}' ) )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:635:1: (otherlv_0= 'constructor' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_features_3_0= ruleOperationFeature ) )* otherlv_4= '}' )
            {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:635:1: (otherlv_0= 'constructor' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_features_3_0= ruleOperationFeature ) )* otherlv_4= '}' )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:635:3: otherlv_0= 'constructor' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_features_3_0= ruleOperationFeature ) )* otherlv_4= '}'
            {
            otherlv_0=(Token)match(input,23,FOLLOW_23_in_ruleModelConstructorStep1493); 

                	newLeafNode(otherlv_0, grammarAccess.getModelConstructorStepAccess().getConstructorKeyword_0());
                
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:639:1: ( (lv_name_1_0= RULE_ID ) )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:640:1: (lv_name_1_0= RULE_ID )
            {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:640:1: (lv_name_1_0= RULE_ID )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:641:3: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleModelConstructorStep1510); 

            			newLeafNode(lv_name_1_0, grammarAccess.getModelConstructorStepAccess().getNameIDTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getModelConstructorStepRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_1_0, 
                    		"ID");
            	    

            }


            }

            otherlv_2=(Token)match(input,13,FOLLOW_13_in_ruleModelConstructorStep1527); 

                	newLeafNode(otherlv_2, grammarAccess.getModelConstructorStepAccess().getLeftCurlyBracketKeyword_2());
                
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:661:1: ( (lv_features_3_0= ruleOperationFeature ) )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( ((LA9_0>=25 && LA9_0<=26)||(LA9_0>=28 && LA9_0<=29)) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:662:1: (lv_features_3_0= ruleOperationFeature )
            	    {
            	    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:662:1: (lv_features_3_0= ruleOperationFeature )
            	    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:663:3: lv_features_3_0= ruleOperationFeature
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getModelConstructorStepAccess().getFeaturesOperationFeatureParserRuleCall_3_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleOperationFeature_in_ruleModelConstructorStep1548);
            	    lv_features_3_0=ruleOperationFeature();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getModelConstructorStepRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"features",
            	            		lv_features_3_0, 
            	            		"OperationFeature");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            otherlv_4=(Token)match(input,14,FOLLOW_14_in_ruleModelConstructorStep1561); 

                	newLeafNode(otherlv_4, grammarAccess.getModelConstructorStepAccess().getRightCurlyBracketKeyword_4());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleModelConstructorStep"


    // $ANTLR start "entryRuleModelOperationStep"
    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:691:1: entryRuleModelOperationStep returns [EObject current=null] : iv_ruleModelOperationStep= ruleModelOperationStep EOF ;
    public final EObject entryRuleModelOperationStep() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModelOperationStep = null;


        try {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:692:2: (iv_ruleModelOperationStep= ruleModelOperationStep EOF )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:693:2: iv_ruleModelOperationStep= ruleModelOperationStep EOF
            {
             newCompositeNode(grammarAccess.getModelOperationStepRule()); 
            pushFollow(FOLLOW_ruleModelOperationStep_in_entryRuleModelOperationStep1597);
            iv_ruleModelOperationStep=ruleModelOperationStep();

            state._fsp--;

             current =iv_ruleModelOperationStep; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleModelOperationStep1607); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleModelOperationStep"


    // $ANTLR start "ruleModelOperationStep"
    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:700:1: ruleModelOperationStep returns [EObject current=null] : (otherlv_0= 'op' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_features_3_0= ruleOperationFeature ) )* otherlv_4= '}' ) ;
    public final EObject ruleModelOperationStep() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_features_3_0 = null;


         enterRule(); 
            
        try {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:703:28: ( (otherlv_0= 'op' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_features_3_0= ruleOperationFeature ) )* otherlv_4= '}' ) )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:704:1: (otherlv_0= 'op' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_features_3_0= ruleOperationFeature ) )* otherlv_4= '}' )
            {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:704:1: (otherlv_0= 'op' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_features_3_0= ruleOperationFeature ) )* otherlv_4= '}' )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:704:3: otherlv_0= 'op' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_features_3_0= ruleOperationFeature ) )* otherlv_4= '}'
            {
            otherlv_0=(Token)match(input,24,FOLLOW_24_in_ruleModelOperationStep1644); 

                	newLeafNode(otherlv_0, grammarAccess.getModelOperationStepAccess().getOpKeyword_0());
                
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:708:1: ( (lv_name_1_0= RULE_ID ) )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:709:1: (lv_name_1_0= RULE_ID )
            {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:709:1: (lv_name_1_0= RULE_ID )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:710:3: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleModelOperationStep1661); 

            			newLeafNode(lv_name_1_0, grammarAccess.getModelOperationStepAccess().getNameIDTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getModelOperationStepRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_1_0, 
                    		"ID");
            	    

            }


            }

            otherlv_2=(Token)match(input,13,FOLLOW_13_in_ruleModelOperationStep1678); 

                	newLeafNode(otherlv_2, grammarAccess.getModelOperationStepAccess().getLeftCurlyBracketKeyword_2());
                
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:730:1: ( (lv_features_3_0= ruleOperationFeature ) )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( ((LA10_0>=25 && LA10_0<=26)||(LA10_0>=28 && LA10_0<=29)) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:731:1: (lv_features_3_0= ruleOperationFeature )
            	    {
            	    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:731:1: (lv_features_3_0= ruleOperationFeature )
            	    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:732:3: lv_features_3_0= ruleOperationFeature
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getModelOperationStepAccess().getFeaturesOperationFeatureParserRuleCall_3_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleOperationFeature_in_ruleModelOperationStep1699);
            	    lv_features_3_0=ruleOperationFeature();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getModelOperationStepRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"features",
            	            		lv_features_3_0, 
            	            		"OperationFeature");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            otherlv_4=(Token)match(input,14,FOLLOW_14_in_ruleModelOperationStep1712); 

                	newLeafNode(otherlv_4, grammarAccess.getModelOperationStepAccess().getRightCurlyBracketKeyword_4());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleModelOperationStep"


    // $ANTLR start "entryRuleOperationFeature"
    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:760:1: entryRuleOperationFeature returns [EObject current=null] : iv_ruleOperationFeature= ruleOperationFeature EOF ;
    public final EObject entryRuleOperationFeature() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperationFeature = null;


        try {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:761:2: (iv_ruleOperationFeature= ruleOperationFeature EOF )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:762:2: iv_ruleOperationFeature= ruleOperationFeature EOF
            {
             newCompositeNode(grammarAccess.getOperationFeatureRule()); 
            pushFollow(FOLLOW_ruleOperationFeature_in_entryRuleOperationFeature1748);
            iv_ruleOperationFeature=ruleOperationFeature();

            state._fsp--;

             current =iv_ruleOperationFeature; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOperationFeature1758); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOperationFeature"


    // $ANTLR start "ruleOperationFeature"
    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:769:1: ruleOperationFeature returns [EObject current=null] : (this_DependencyFeature_0= ruleDependencyFeature | this_ImplementationFeature_1= ruleImplementationFeature | this_ForFeature_2= ruleForFeature | this_SourceFeature_3= ruleSourceFeature ) ;
    public final EObject ruleOperationFeature() throws RecognitionException {
        EObject current = null;

        EObject this_DependencyFeature_0 = null;

        EObject this_ImplementationFeature_1 = null;

        EObject this_ForFeature_2 = null;

        EObject this_SourceFeature_3 = null;


         enterRule(); 
            
        try {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:772:28: ( (this_DependencyFeature_0= ruleDependencyFeature | this_ImplementationFeature_1= ruleImplementationFeature | this_ForFeature_2= ruleForFeature | this_SourceFeature_3= ruleSourceFeature ) )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:773:1: (this_DependencyFeature_0= ruleDependencyFeature | this_ImplementationFeature_1= ruleImplementationFeature | this_ForFeature_2= ruleForFeature | this_SourceFeature_3= ruleSourceFeature )
            {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:773:1: (this_DependencyFeature_0= ruleDependencyFeature | this_ImplementationFeature_1= ruleImplementationFeature | this_ForFeature_2= ruleForFeature | this_SourceFeature_3= ruleSourceFeature )
            int alt11=4;
            switch ( input.LA(1) ) {
            case 28:
                {
                alt11=1;
                }
                break;
            case 29:
                {
                alt11=2;
                }
                break;
            case 26:
                {
                alt11=3;
                }
                break;
            case 25:
                {
                alt11=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }

            switch (alt11) {
                case 1 :
                    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:774:5: this_DependencyFeature_0= ruleDependencyFeature
                    {
                     
                            newCompositeNode(grammarAccess.getOperationFeatureAccess().getDependencyFeatureParserRuleCall_0()); 
                        
                    pushFollow(FOLLOW_ruleDependencyFeature_in_ruleOperationFeature1805);
                    this_DependencyFeature_0=ruleDependencyFeature();

                    state._fsp--;

                     
                            current = this_DependencyFeature_0; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 2 :
                    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:784:5: this_ImplementationFeature_1= ruleImplementationFeature
                    {
                     
                            newCompositeNode(grammarAccess.getOperationFeatureAccess().getImplementationFeatureParserRuleCall_1()); 
                        
                    pushFollow(FOLLOW_ruleImplementationFeature_in_ruleOperationFeature1832);
                    this_ImplementationFeature_1=ruleImplementationFeature();

                    state._fsp--;

                     
                            current = this_ImplementationFeature_1; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 3 :
                    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:794:5: this_ForFeature_2= ruleForFeature
                    {
                     
                            newCompositeNode(grammarAccess.getOperationFeatureAccess().getForFeatureParserRuleCall_2()); 
                        
                    pushFollow(FOLLOW_ruleForFeature_in_ruleOperationFeature1859);
                    this_ForFeature_2=ruleForFeature();

                    state._fsp--;

                     
                            current = this_ForFeature_2; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;
                case 4 :
                    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:804:5: this_SourceFeature_3= ruleSourceFeature
                    {
                     
                            newCompositeNode(grammarAccess.getOperationFeatureAccess().getSourceFeatureParserRuleCall_3()); 
                        
                    pushFollow(FOLLOW_ruleSourceFeature_in_ruleOperationFeature1886);
                    this_SourceFeature_3=ruleSourceFeature();

                    state._fsp--;

                     
                            current = this_SourceFeature_3; 
                            afterParserOrEnumRuleCall();
                        

                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOperationFeature"


    // $ANTLR start "entryRuleSourceFeature"
    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:820:1: entryRuleSourceFeature returns [EObject current=null] : iv_ruleSourceFeature= ruleSourceFeature EOF ;
    public final EObject entryRuleSourceFeature() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSourceFeature = null;


        try {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:821:2: (iv_ruleSourceFeature= ruleSourceFeature EOF )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:822:2: iv_ruleSourceFeature= ruleSourceFeature EOF
            {
             newCompositeNode(grammarAccess.getSourceFeatureRule()); 
            pushFollow(FOLLOW_ruleSourceFeature_in_entryRuleSourceFeature1921);
            iv_ruleSourceFeature=ruleSourceFeature();

            state._fsp--;

             current =iv_ruleSourceFeature; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSourceFeature1931); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSourceFeature"


    // $ANTLR start "ruleSourceFeature"
    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:829:1: ruleSourceFeature returns [EObject current=null] : (otherlv_0= 'source' ( (otherlv_1= RULE_ID ) ) ) ;
    public final EObject ruleSourceFeature() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;

         enterRule(); 
            
        try {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:832:28: ( (otherlv_0= 'source' ( (otherlv_1= RULE_ID ) ) ) )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:833:1: (otherlv_0= 'source' ( (otherlv_1= RULE_ID ) ) )
            {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:833:1: (otherlv_0= 'source' ( (otherlv_1= RULE_ID ) ) )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:833:3: otherlv_0= 'source' ( (otherlv_1= RULE_ID ) )
            {
            otherlv_0=(Token)match(input,25,FOLLOW_25_in_ruleSourceFeature1968); 

                	newLeafNode(otherlv_0, grammarAccess.getSourceFeatureAccess().getSourceKeyword_0());
                
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:837:1: ( (otherlv_1= RULE_ID ) )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:838:1: (otherlv_1= RULE_ID )
            {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:838:1: (otherlv_1= RULE_ID )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:839:3: otherlv_1= RULE_ID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getSourceFeatureRule());
            	        }
                    
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleSourceFeature1988); 

            		newLeafNode(otherlv_1, grammarAccess.getSourceFeatureAccess().getModuleSetModuleSetCrossReference_1_0()); 
            	

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSourceFeature"


    // $ANTLR start "entryRuleForFeature"
    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:858:1: entryRuleForFeature returns [EObject current=null] : iv_ruleForFeature= ruleForFeature EOF ;
    public final EObject entryRuleForFeature() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleForFeature = null;


        try {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:859:2: (iv_ruleForFeature= ruleForFeature EOF )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:860:2: iv_ruleForFeature= ruleForFeature EOF
            {
             newCompositeNode(grammarAccess.getForFeatureRule()); 
            pushFollow(FOLLOW_ruleForFeature_in_entryRuleForFeature2024);
            iv_ruleForFeature=ruleForFeature();

            state._fsp--;

             current =iv_ruleForFeature; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleForFeature2034); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleForFeature"


    // $ANTLR start "ruleForFeature"
    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:867:1: ruleForFeature returns [EObject current=null] : (otherlv_0= 'for' ( (lv_loopVar_1_0= RULE_ID ) ) otherlv_2= 'in' ( (lv_arrayVar_3_0= RULE_ID ) ) otherlv_4= '{' ( (lv_features_5_0= ruleOperationFeature ) )* otherlv_6= '}' ) ;
    public final EObject ruleForFeature() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_loopVar_1_0=null;
        Token otherlv_2=null;
        Token lv_arrayVar_3_0=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_features_5_0 = null;


         enterRule(); 
            
        try {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:870:28: ( (otherlv_0= 'for' ( (lv_loopVar_1_0= RULE_ID ) ) otherlv_2= 'in' ( (lv_arrayVar_3_0= RULE_ID ) ) otherlv_4= '{' ( (lv_features_5_0= ruleOperationFeature ) )* otherlv_6= '}' ) )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:871:1: (otherlv_0= 'for' ( (lv_loopVar_1_0= RULE_ID ) ) otherlv_2= 'in' ( (lv_arrayVar_3_0= RULE_ID ) ) otherlv_4= '{' ( (lv_features_5_0= ruleOperationFeature ) )* otherlv_6= '}' )
            {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:871:1: (otherlv_0= 'for' ( (lv_loopVar_1_0= RULE_ID ) ) otherlv_2= 'in' ( (lv_arrayVar_3_0= RULE_ID ) ) otherlv_4= '{' ( (lv_features_5_0= ruleOperationFeature ) )* otherlv_6= '}' )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:871:3: otherlv_0= 'for' ( (lv_loopVar_1_0= RULE_ID ) ) otherlv_2= 'in' ( (lv_arrayVar_3_0= RULE_ID ) ) otherlv_4= '{' ( (lv_features_5_0= ruleOperationFeature ) )* otherlv_6= '}'
            {
            otherlv_0=(Token)match(input,26,FOLLOW_26_in_ruleForFeature2071); 

                	newLeafNode(otherlv_0, grammarAccess.getForFeatureAccess().getForKeyword_0());
                
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:875:1: ( (lv_loopVar_1_0= RULE_ID ) )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:876:1: (lv_loopVar_1_0= RULE_ID )
            {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:876:1: (lv_loopVar_1_0= RULE_ID )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:877:3: lv_loopVar_1_0= RULE_ID
            {
            lv_loopVar_1_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleForFeature2088); 

            			newLeafNode(lv_loopVar_1_0, grammarAccess.getForFeatureAccess().getLoopVarIDTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getForFeatureRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"loopVar",
                    		lv_loopVar_1_0, 
                    		"ID");
            	    

            }


            }

            otherlv_2=(Token)match(input,27,FOLLOW_27_in_ruleForFeature2105); 

                	newLeafNode(otherlv_2, grammarAccess.getForFeatureAccess().getInKeyword_2());
                
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:897:1: ( (lv_arrayVar_3_0= RULE_ID ) )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:898:1: (lv_arrayVar_3_0= RULE_ID )
            {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:898:1: (lv_arrayVar_3_0= RULE_ID )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:899:3: lv_arrayVar_3_0= RULE_ID
            {
            lv_arrayVar_3_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleForFeature2122); 

            			newLeafNode(lv_arrayVar_3_0, grammarAccess.getForFeatureAccess().getArrayVarIDTerminalRuleCall_3_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getForFeatureRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"arrayVar",
                    		lv_arrayVar_3_0, 
                    		"ID");
            	    

            }


            }

            otherlv_4=(Token)match(input,13,FOLLOW_13_in_ruleForFeature2139); 

                	newLeafNode(otherlv_4, grammarAccess.getForFeatureAccess().getLeftCurlyBracketKeyword_4());
                
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:919:1: ( (lv_features_5_0= ruleOperationFeature ) )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( ((LA12_0>=25 && LA12_0<=26)||(LA12_0>=28 && LA12_0<=29)) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:920:1: (lv_features_5_0= ruleOperationFeature )
            	    {
            	    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:920:1: (lv_features_5_0= ruleOperationFeature )
            	    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:921:3: lv_features_5_0= ruleOperationFeature
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getForFeatureAccess().getFeaturesOperationFeatureParserRuleCall_5_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleOperationFeature_in_ruleForFeature2160);
            	    lv_features_5_0=ruleOperationFeature();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getForFeatureRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"features",
            	            		lv_features_5_0, 
            	            		"OperationFeature");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);

            otherlv_6=(Token)match(input,14,FOLLOW_14_in_ruleForFeature2173); 

                	newLeafNode(otherlv_6, grammarAccess.getForFeatureAccess().getRightCurlyBracketKeyword_6());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleForFeature"


    // $ANTLR start "entryRuleDependencyFeature"
    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:949:1: entryRuleDependencyFeature returns [EObject current=null] : iv_ruleDependencyFeature= ruleDependencyFeature EOF ;
    public final EObject entryRuleDependencyFeature() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDependencyFeature = null;


        try {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:950:2: (iv_ruleDependencyFeature= ruleDependencyFeature EOF )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:951:2: iv_ruleDependencyFeature= ruleDependencyFeature EOF
            {
             newCompositeNode(grammarAccess.getDependencyFeatureRule()); 
            pushFollow(FOLLOW_ruleDependencyFeature_in_entryRuleDependencyFeature2209);
            iv_ruleDependencyFeature=ruleDependencyFeature();

            state._fsp--;

             current =iv_ruleDependencyFeature; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDependencyFeature2219); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleDependencyFeature"


    // $ANTLR start "ruleDependencyFeature"
    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:958:1: ruleDependencyFeature returns [EObject current=null] : (otherlv_0= 'dependency' ( (otherlv_1= RULE_ID ) ) (otherlv_2= '{' ( (lv_variables_3_0= ruleVariable ) )* otherlv_4= '}' )? ( (lv_name_5_0= RULE_ID ) )? ) ;
    public final EObject ruleDependencyFeature() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token lv_name_5_0=null;
        EObject lv_variables_3_0 = null;


         enterRule(); 
            
        try {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:961:28: ( (otherlv_0= 'dependency' ( (otherlv_1= RULE_ID ) ) (otherlv_2= '{' ( (lv_variables_3_0= ruleVariable ) )* otherlv_4= '}' )? ( (lv_name_5_0= RULE_ID ) )? ) )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:962:1: (otherlv_0= 'dependency' ( (otherlv_1= RULE_ID ) ) (otherlv_2= '{' ( (lv_variables_3_0= ruleVariable ) )* otherlv_4= '}' )? ( (lv_name_5_0= RULE_ID ) )? )
            {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:962:1: (otherlv_0= 'dependency' ( (otherlv_1= RULE_ID ) ) (otherlv_2= '{' ( (lv_variables_3_0= ruleVariable ) )* otherlv_4= '}' )? ( (lv_name_5_0= RULE_ID ) )? )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:962:3: otherlv_0= 'dependency' ( (otherlv_1= RULE_ID ) ) (otherlv_2= '{' ( (lv_variables_3_0= ruleVariable ) )* otherlv_4= '}' )? ( (lv_name_5_0= RULE_ID ) )?
            {
            otherlv_0=(Token)match(input,28,FOLLOW_28_in_ruleDependencyFeature2256); 

                	newLeafNode(otherlv_0, grammarAccess.getDependencyFeatureAccess().getDependencyKeyword_0());
                
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:966:1: ( (otherlv_1= RULE_ID ) )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:967:1: (otherlv_1= RULE_ID )
            {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:967:1: (otherlv_1= RULE_ID )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:968:3: otherlv_1= RULE_ID
            {

            			if (current==null) {
            	            current = createModelElement(grammarAccess.getDependencyFeatureRule());
            	        }
                    
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleDependencyFeature2276); 

            		newLeafNode(otherlv_1, grammarAccess.getDependencyFeatureAccess().getStepStepCrossReference_1_0()); 
            	

            }


            }

            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:979:2: (otherlv_2= '{' ( (lv_variables_3_0= ruleVariable ) )* otherlv_4= '}' )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==13) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:979:4: otherlv_2= '{' ( (lv_variables_3_0= ruleVariable ) )* otherlv_4= '}'
                    {
                    otherlv_2=(Token)match(input,13,FOLLOW_13_in_ruleDependencyFeature2289); 

                        	newLeafNode(otherlv_2, grammarAccess.getDependencyFeatureAccess().getLeftCurlyBracketKeyword_2_0());
                        
                    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:983:1: ( (lv_variables_3_0= ruleVariable ) )*
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( (LA13_0==19) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:984:1: (lv_variables_3_0= ruleVariable )
                    	    {
                    	    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:984:1: (lv_variables_3_0= ruleVariable )
                    	    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:985:3: lv_variables_3_0= ruleVariable
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getDependencyFeatureAccess().getVariablesVariableParserRuleCall_2_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleVariable_in_ruleDependencyFeature2310);
                    	    lv_variables_3_0=ruleVariable();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getDependencyFeatureRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"variables",
                    	            		lv_variables_3_0, 
                    	            		"Variable");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop13;
                        }
                    } while (true);

                    otherlv_4=(Token)match(input,14,FOLLOW_14_in_ruleDependencyFeature2323); 

                        	newLeafNode(otherlv_4, grammarAccess.getDependencyFeatureAccess().getRightCurlyBracketKeyword_2_2());
                        

                    }
                    break;

            }

            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:1005:3: ( (lv_name_5_0= RULE_ID ) )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==RULE_ID) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:1006:1: (lv_name_5_0= RULE_ID )
                    {
                    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:1006:1: (lv_name_5_0= RULE_ID )
                    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:1007:3: lv_name_5_0= RULE_ID
                    {
                    lv_name_5_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleDependencyFeature2342); 

                    			newLeafNode(lv_name_5_0, grammarAccess.getDependencyFeatureAccess().getNameIDTerminalRuleCall_3_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getDependencyFeatureRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"name",
                            		lv_name_5_0, 
                            		"ID");
                    	    

                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleDependencyFeature"


    // $ANTLR start "entryRuleImplementationFeature"
    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:1031:1: entryRuleImplementationFeature returns [EObject current=null] : iv_ruleImplementationFeature= ruleImplementationFeature EOF ;
    public final EObject entryRuleImplementationFeature() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleImplementationFeature = null;


        try {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:1032:2: (iv_ruleImplementationFeature= ruleImplementationFeature EOF )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:1033:2: iv_ruleImplementationFeature= ruleImplementationFeature EOF
            {
             newCompositeNode(grammarAccess.getImplementationFeatureRule()); 
            pushFollow(FOLLOW_ruleImplementationFeature_in_entryRuleImplementationFeature2384);
            iv_ruleImplementationFeature=ruleImplementationFeature();

            state._fsp--;

             current =iv_ruleImplementationFeature; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleImplementationFeature2394); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleImplementationFeature"


    // $ANTLR start "ruleImplementationFeature"
    // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:1040:1: ruleImplementationFeature returns [EObject current=null] : (otherlv_0= 'implementation' ( (lv_implementation_1_0= RULE_STRING ) ) ) ;
    public final EObject ruleImplementationFeature() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_implementation_1_0=null;

         enterRule(); 
            
        try {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:1043:28: ( (otherlv_0= 'implementation' ( (lv_implementation_1_0= RULE_STRING ) ) ) )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:1044:1: (otherlv_0= 'implementation' ( (lv_implementation_1_0= RULE_STRING ) ) )
            {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:1044:1: (otherlv_0= 'implementation' ( (lv_implementation_1_0= RULE_STRING ) ) )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:1044:3: otherlv_0= 'implementation' ( (lv_implementation_1_0= RULE_STRING ) )
            {
            otherlv_0=(Token)match(input,29,FOLLOW_29_in_ruleImplementationFeature2431); 

                	newLeafNode(otherlv_0, grammarAccess.getImplementationFeatureAccess().getImplementationKeyword_0());
                
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:1048:1: ( (lv_implementation_1_0= RULE_STRING ) )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:1049:1: (lv_implementation_1_0= RULE_STRING )
            {
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:1049:1: (lv_implementation_1_0= RULE_STRING )
            // ../workflowdsl/src-gen/de/jpwinkler/daf/workflowdsl/parser/antlr/internal/InternalWorkflowDsl.g:1050:3: lv_implementation_1_0= RULE_STRING
            {
            lv_implementation_1_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleImplementationFeature2448); 

            			newLeafNode(lv_implementation_1_0, grammarAccess.getImplementationFeatureAccess().getImplementationSTRINGTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getImplementationFeatureRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"implementation",
                    		lv_implementation_1_0, 
                    		"STRING");
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleImplementationFeature"

    // Delegated rules


 

    public static final BitSet FOLLOW_ruleWorkflowModel_in_entryRuleWorkflowModel75 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleWorkflowModel85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleWorkflowElement_in_ruleWorkflowModel130 = new BitSet(new long[]{0x0000000001881802L});
    public static final BitSet FOLLOW_ruleWorkflowElement_in_entryRuleWorkflowElement166 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleWorkflowElement176 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTarget_in_ruleWorkflowElement223 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStep_in_ruleWorkflowElement250 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleModuleSet_in_ruleWorkflowElement277 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleVariable_in_ruleWorkflowElement304 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTarget_in_entryRuleTarget339 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTarget349 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_ruleTarget386 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleTarget406 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleModuleSet_in_entryRuleModuleSet442 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleModuleSet452 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_12_in_ruleModuleSet489 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleModuleSet506 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleModuleSet523 = new BitSet(new long[]{0x000000000007C000L});
    public static final BitSet FOLLOW_ruleModuleSetEntry_in_ruleModuleSet544 = new BitSet(new long[]{0x000000000007C000L});
    public static final BitSet FOLLOW_14_in_ruleModuleSet557 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMODULE_SET_ENTRY_TYPE_in_entryRuleMODULE_SET_ENTRY_TYPE594 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleMODULE_SET_ENTRY_TYPE605 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_ruleMODULE_SET_ENTRY_TYPE643 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_16_in_ruleMODULE_SET_ENTRY_TYPE662 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_ruleMODULE_SET_ENTRY_TYPE681 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_ruleMODULE_SET_ENTRY_TYPE700 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleModuleSetEntry_in_entryRuleModuleSetEntry740 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleModuleSetEntry750 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMODULE_SET_ENTRY_TYPE_in_ruleModuleSetEntry796 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleModuleSetEntry813 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleVariable_in_entryRuleVariable854 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleVariable864 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSimpleVariable_in_ruleVariable911 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleArrayVariable_in_ruleVariable938 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSimpleVariable_in_entryRuleSimpleVariable973 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSimpleVariable983 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_ruleSimpleVariable1020 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleSimpleVariable1037 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleSimpleVariable1054 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleSimpleVariable1071 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleArrayVariable_in_entryRuleArrayVariable1112 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleArrayVariable1122 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_ruleArrayVariable1159 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleArrayVariable1176 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_ruleArrayVariable1193 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleArrayVariable1205 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleArrayVariable1217 = new BitSet(new long[]{0x0000000000004020L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleArrayVariable1235 = new BitSet(new long[]{0x0000000000404000L});
    public static final BitSet FOLLOW_22_in_ruleArrayVariable1253 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleArrayVariable1270 = new BitSet(new long[]{0x0000000000404000L});
    public static final BitSet FOLLOW_14_in_ruleArrayVariable1291 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleStep_in_entryRuleStep1327 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleStep1337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleModelConstructorStep_in_ruleStep1384 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleModelOperationStep_in_ruleStep1411 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleModelConstructorStep_in_entryRuleModelConstructorStep1446 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleModelConstructorStep1456 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_ruleModelConstructorStep1493 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleModelConstructorStep1510 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleModelConstructorStep1527 = new BitSet(new long[]{0x0000000036004000L});
    public static final BitSet FOLLOW_ruleOperationFeature_in_ruleModelConstructorStep1548 = new BitSet(new long[]{0x0000000036004000L});
    public static final BitSet FOLLOW_14_in_ruleModelConstructorStep1561 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleModelOperationStep_in_entryRuleModelOperationStep1597 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleModelOperationStep1607 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_ruleModelOperationStep1644 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleModelOperationStep1661 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleModelOperationStep1678 = new BitSet(new long[]{0x0000000036004000L});
    public static final BitSet FOLLOW_ruleOperationFeature_in_ruleModelOperationStep1699 = new BitSet(new long[]{0x0000000036004000L});
    public static final BitSet FOLLOW_14_in_ruleModelOperationStep1712 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleOperationFeature_in_entryRuleOperationFeature1748 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOperationFeature1758 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDependencyFeature_in_ruleOperationFeature1805 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleImplementationFeature_in_ruleOperationFeature1832 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleForFeature_in_ruleOperationFeature1859 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSourceFeature_in_ruleOperationFeature1886 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSourceFeature_in_entryRuleSourceFeature1921 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSourceFeature1931 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_ruleSourceFeature1968 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleSourceFeature1988 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleForFeature_in_entryRuleForFeature2024 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleForFeature2034 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_ruleForFeature2071 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleForFeature2088 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleForFeature2105 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleForFeature2122 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleForFeature2139 = new BitSet(new long[]{0x0000000036004000L});
    public static final BitSet FOLLOW_ruleOperationFeature_in_ruleForFeature2160 = new BitSet(new long[]{0x0000000036004000L});
    public static final BitSet FOLLOW_14_in_ruleForFeature2173 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDependencyFeature_in_entryRuleDependencyFeature2209 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDependencyFeature2219 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_ruleDependencyFeature2256 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleDependencyFeature2276 = new BitSet(new long[]{0x0000000000002012L});
    public static final BitSet FOLLOW_13_in_ruleDependencyFeature2289 = new BitSet(new long[]{0x0000000001885800L});
    public static final BitSet FOLLOW_ruleVariable_in_ruleDependencyFeature2310 = new BitSet(new long[]{0x0000000001885800L});
    public static final BitSet FOLLOW_14_in_ruleDependencyFeature2323 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleDependencyFeature2342 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleImplementationFeature_in_entryRuleImplementationFeature2384 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleImplementationFeature2394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_ruleImplementationFeature2431 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleImplementationFeature2448 = new BitSet(new long[]{0x0000000000000002L});

}