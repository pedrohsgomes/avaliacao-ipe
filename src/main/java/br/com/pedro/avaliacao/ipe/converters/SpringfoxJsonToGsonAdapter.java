package br.com.pedro.avaliacao.ipe.converters;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import springfox.documentation.spring.web.json.Json;

public class SpringfoxJsonToGsonAdapter implements JsonSerializer<Json> {

	@Override
	public JsonElement serialize(Json json, Type typeOfSrc, JsonSerializationContext context) {
		final JsonParser parser = new JsonParser();
        return parser.parse(json.value());
	}
}