package model.jsonUtility;

public interface ResponseTransformer {

    String render(Object model) throws Exception;

}