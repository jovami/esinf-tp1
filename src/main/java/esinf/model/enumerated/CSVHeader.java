package esinf.model.enumerated;

public enum CSVHeader {
    HEADER1(14, ",") {
        @Override
        public String toString() {
            return "Domain Code,Domain,Area Code (FAO),Area,Element Code,Element,Item Code (FAO),Item,Year Code,Year,Unit,Value,Flag,Flag Description";
        }
    };

    private final int columns;

    private final String delimiter;

    public int getColumnCount()
    {
        return this.columns;
    }

    public String getDelimiter()
    {
        return this.delimiter;
    }

    CSVHeader(int col, String delim)
    {
        this.columns = col;
        this.delimiter = delim;
    }
}
