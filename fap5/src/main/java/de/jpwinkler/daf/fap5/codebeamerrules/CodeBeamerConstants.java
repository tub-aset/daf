package de.jpwinkler.daf.fap5.codebeamerrules;

public final class CodeBeamerConstants {

    public static final String ATTRIBUTE_FO_OBJECT_TYPE = "FO_Object_Type";
    public static final String ATTRIBUTE_OBJECT_TYPE = "Object Type";
    public static final String ATTRIBUTE_COMMENT = "comment";

    public static final String MARKER_COMMENT = "comment";
    public static final String MARKER_DUPLICATED_LITERAL = "duplicated_literal";
    public static final String MARKER_ELLIPSIS = "ellipsis";
    public static final String MARKER_EMPTY_HEADING = "empty_heading";
    public static final String MARKER_EMPTY_HEADING_LEVEL = "level";
    public static final String MARKER_HEADING = "heading";
    public static final String MARKER_HEADING_AS_REQUIREMENT = "heading_as_requirement";
    public static final String MARKER_HIGH_SENTENCE_COUNT = "high_sentence_count";
    public static final String MARKER_ILLEGAL_FORMATTING = "illegal_formatting";
    public static final String MARKER_INFORMATION = "information";
    public static final String MARKER_INLINED_CHAPTERS = "inlined_chapters";
    public static final String MARKER_QUESTION_MARK = "question_mark";
    public static final String MARKER_REQUIREMENT = "requirement";
    public static final String MARKER_REQUIREMENT_LENGTH = "length";
    public static final String MARKER_TBD = "tbd";
    public static final String MARKER_TBV = "tbv";
    public static final String MARKER_TODO = "todo";
    public static final String MARKER_WIKILINK = "wikilink";

    public static final long SEVERITY_COMMENT = 10;
    public static final long SEVERITY_DUPLICATED_LITERAL = 5;
    public static final long SEVERITY_ELLIPSIS = 5;
    public static final long[] SEVERITY_EMPTY_HEADING = new long[] { 50, 20, 10, 5, 2, 1, 1, 1, 1 };
    public static final long SEVERITY_HEADING_AS_REQUIREMENT = 10;
    public static final long SEVERITY_ILLEGAL_FO_OBJECT_TYPE = 2;
    public static final long SEVERITY_ILLEGAL_FORMATTING = 5;
    public static final long SEVERITY_INLINED_CHAPTERS = 10;
    public static final long SEVERITY_OBJECT_TEXT_AND_HEADING = 100;
    public static final long SEVERITY_QUESTION_MARK = 30;
    public static final long SEVERITY_SENTENCE_COUNT = 20;
    public static final long SEVERITY_TBD = 40;
    public static final long SEVERITY_TODO = 40;
    public static final long SEVERITY_TBV = 40;
    public static final long SEVERITY_WIKILINK = 10;
    public static final long SEVERITY_MATURITY_OPEN = 5;
    public static final long SEVERITY_MATURITY_SPECIFIED = 2;
    public static final long SEVERITY_MATURITY_FOLLOW_UP_HASHTAGS = 20;
    public static final long SEVERITY_MATURITY_FOLLOW_UP = 10;

    public static final String METRIC_REQUIREMENT_COUNT = "requirementCount";
    public static final String METRIC_HEADING_AS_REQUIREMENT_COUNT = "headingAsRequirementCount";
    public static final String METRIC_OPEN_TODOS = "openTodos";
    public static final String METRIC_MATURITY_OPEN_COUNT = "maturityOpen";
    public static final String METRIC_MATURITY_SPECIFIED_COUNT = "maturitySpecified";
    public static final String METRIC_MATURITY_FOLLOW_UP_COUNT = "maturityFollowUp";
    public static final String METRIC_MATURITY_FOLLOW_UP_HASHTAGS_COUNT = "maturityFollowUpHashTags";
    public static final String METRIC_MATURITY_AGREED_COUNT = "maturityAgreed";

    public static final String METRIC_ACCEPTANCE_NONE_COUNT = "acceptanceNone";
    public static final String METRIC_ACCEPTANCE_DELETED_REQ_COUNT = "acceptanceDeletedReq";
    public static final String METRIC_ACCEPTANCE_CHANGED_REQ_COUNT = "acceptanceChangedReq";
    public static final String METRIC_ACCEPTANCE_TO_CLARIFY_COUNT = "acceptanceToClarify";
    public static final String METRIC_ACCEPTANCE_CONFLICT_COUNT = "acceptanceConflict";
    public static final String METRIC_ACCEPTANCE_PARTLY_AGREED_COUNT = "acceptancePartlyAgreed";
    public static final String METRIC_ACCEPTANCE_AGREED_COUNT = "acceptanceAgreed";

    public static final String MARKER_ACCEPTANCE_NONE = "acceptance_none";
    public static final String MARKER_ACCEPTANCE_DELETED_REQ = "acceptance_deleted_req";
    public static final String MARKER_ACCEPTANCE_CHANGED_REQ = "acceptance_changed_req";
    public static final String MARKER_ACCEPTANCE_TO_CLARIFY = "acceptance_to_clarify";
    public static final String MARKER_ACCEPTANCE_PARTLY_AGREED = "acceptance_partly_agreed";
    public static final String MARKER_ACCEPTANCE_AGREED = "acceptance_agreed";
    public static final String MARKER_ACCEPTANCE_CONFLICT = "acceptance_conflict";

    public static final String MARKER_MATURITY_OPEN = "maturity_open";
    public static final String MARKER_MATURITY_AGREED = "maturity_agreed";
    public static final String MARKER_MATURITY_FOLLOW_UP = "maturity_follow_up";
    public static final String MARKER_MATURITY_FOLLOW_UP_HASHTAGS = "maturity_follow_up_hashtags";
    public static final String MARKER_MATURITY_SPECIFIED = "maturity_specified";
    public static final long SEVERITY_EMPTY_OBJECT_TYPE = 30;
    public static final String MARKER_EMPTY_OBJECT_TYPE = "empty_object_type";
    public static final String METRIC_EMPTY_OBJECT_TYPE = "emptyObjectType";
    public static final long SEVERITY_HEADING_WITH_LINK = 10;
    public static final String MARKER_HEADING_WITH_LINK = "heading_with_link";
    public static final String MARKER_INFORMATION_WITH_LINK = "information_with_link";
    public static final long SEVERITY_INFORMATION_WITH_LINK = 10;
    public static final String MARKER_REQUIREMENT_WITHOUT_LINK = "requirement_without_link";
    public static final long SEVERITY_REQUIREMENT_WITHOUT_LINK = 10;

    public static final String METRIC_INFORMATION_WITH_LINK = "informationWithLink";
    public static final String METRIC_HEADING_WITH_LINK = "headingWithLink";
    public static final String METRIC_REQUIREMENT_WITHOUT_LINK = "requirementWithoutLink";
    public static final String METRIC_EMPTY_HEADING = "emptyHeading";
    public static final String METRIC_ESTIMATED_REMAINING_WORK = "estimatedRemainingWork";

    // TODO


    private CodeBeamerConstants() {
    }

}
