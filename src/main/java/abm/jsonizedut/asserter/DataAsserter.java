package abm.jsonizedut.asserter;

import abm.jsonizedut.exceptions.DataAssertionFailed;
import org.json.JSONException;
import org.skyscreamer.jsonassert.FieldComparisonFailure;
import org.skyscreamer.jsonassert.JSONCompare;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONCompareResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DataAsserter {

    private StringBuilder errorStringBuilder;

    private boolean isNotEmpty(Collection collection) {
        return null != collection && collection.size() > 0;
    }

    public void assertData(String expected, String actual, List<String> dynamicFieldsToExclude) throws JSONException, DataAssertionFailed {
        JSONCompareResult jsonCompareResult = JSONCompare.compareJSON(expected, actual, JSONCompareMode.STRICT);
        List<FieldComparisonFailure> fieldComparisonFailures = new ArrayList<>();
        fieldComparisonFailures.addAll(jsonCompareResult.getFieldFailures());
        fieldComparisonFailures.addAll(jsonCompareResult.getFieldMissing());
        fieldComparisonFailures.addAll(jsonCompareResult.getFieldUnexpected());

        Predicate<FieldComparisonFailure> dynamicFieldsPredicate = isNotEmpty(dynamicFieldsToExclude) ?
                $ -> dynamicFieldsToExclude.indexOf($.getActual()) == -1 : $ -> true;

        List<FieldComparisonFailure> anyAdditionalFieldMismatched = fieldComparisonFailures.stream()
                .filter(dynamicFieldsPredicate).collect(Collectors.toList());

        if (isNotEmpty(fieldComparisonFailures) && isNotEmpty(anyAdditionalFieldMismatched)) {
            errorStringBuilder = new StringBuilder("Data Mismatched !\n");
            displayResults(jsonCompareResult);
            throw new DataAssertionFailed(errorStringBuilder.toString());
        }

        if (isNotEmpty(anyAdditionalFieldMismatched)) {
            String mismatchedFieldPaths = anyAdditionalFieldMismatched.stream().map(FieldComparisonFailure::getField)
                    .collect(Collectors.joining("\n"));
            throw new DataAssertionFailed("There are additional fields mismatch. They are :\n" + mismatchedFieldPaths + "\n");
        }
    }


    private void displayResults(JSONCompareResult compareResult) {
        if (isNotEmpty(compareResult.getFieldFailures())) {
            errorStringBuilder.append("Field Failures");
            compareResult.getFieldFailures().forEach(this::displayResults);
        } else if (isNotEmpty(compareResult.getFieldMissing())) {
            errorStringBuilder.append("Missing Fields");
            compareResult.getFieldMissing().forEach(this::displayResults);
        } else if (isNotEmpty(compareResult.getFieldUnexpected())) {
            errorStringBuilder.append("UnExpected Fields");
            compareResult.getFieldUnexpected().forEach(this::displayResults);
        }
    }

    private void displayResults(FieldComparisonFailure fieldComparisonFailure) {
        errorStringBuilder.append("\n\t\tField Path : ").append(fieldComparisonFailure.getField());
        errorStringBuilder.append("\n\t\t[ Expected - ")
                .append(fieldComparisonFailure.getExpected())
                .append(", Actual - ")
                .append(fieldComparisonFailure.getActual())
                .append("]");
    }

    public void assertData(String expected, String actual) throws JSONException, DataAssertionFailed {
        assertData(expected, actual, Collections.emptyList());
    }

}
