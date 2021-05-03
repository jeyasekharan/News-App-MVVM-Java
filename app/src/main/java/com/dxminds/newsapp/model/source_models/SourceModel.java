package com.dxminds.newsapp.model.source_models;

public class SourceModel
{
    private Sources[] sources;

    private String status;

    public Sources[] getSources ()
    {
        return sources;
    }

    public void setSources (Sources[] sources)
    {
        this.sources = sources;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }



    public static class Sources
    {
        private String country;

        private String name;

        private String description;

        private String language;

        private String id;

        private String category;

        private String url;

        public String getCountry ()
        {
            return country;
        }

        public void setCountry (String country)
        {
            this.country = country;
        }

        public String getName ()
        {
            return name;
        }

        public void setName (String name)
        {
            this.name = name;
        }

        public String getDescription ()
        {
            return description;
        }

        public void setDescription (String description)
        {
            this.description = description;
        }

        public String getLanguage ()
        {
            return language;
        }

        public void setLanguage (String language)
        {
            this.language = language;
        }

        public String getId ()
        {
            return id;
        }

        public void setId (String id)
        {
            this.id = id;
        }

        public String getCategory ()
        {
            return category;
        }

        public void setCategory (String category)
        {
            this.category = category;
        }

        public String getUrl ()
        {
            return url;
        }

        public void setUrl (String url)
        {
            this.url = url;
        }

        @Override
        public String toString()
        {
            return "ClassPojo [country = "+country+", name = "+name+", description = "+description+", language = "+language+", id = "+id+", category = "+category+", url = "+url+"]";
        }
    }

}

