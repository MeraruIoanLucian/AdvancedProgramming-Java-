package org.example;

import java.time.LocalDate;
import java.util.List;

public record ImageItem(String name, LocalDate date, List<String> tags, String filePath) { }