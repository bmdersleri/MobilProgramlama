using System.IO;
using System.Text;
using UnityEditor;
using UnityEditor.Callbacks;
using UnityEngine;

namespace Hifive.ProjectBuilder
{
    /// <summary>
    /// Proccess data after build
    /// </summary>
    public class PostBuildActions
    {
        /// <summary>
        /// Run after project build
        /// </summary>
        /// <param name="buildTarget">Platform</param>
        /// <param name="pathToBuiltProject">Path to folder</param>
        [PostProcessBuild]
        public static void PostProcess(BuildTarget buildTarget, string pathToBuiltProject)
        {
            byte[] data = Encoding.UTF8.GetBytes(Application.version + " " + PlayerSettings.iOS.buildNumber);
            string path = Path.Combine(Directory.GetParent(Application.dataPath).FullName, "SupportFiles",
                "version.txt");
            if (File.Exists(path))
            {
                File.Delete(path);
            }

            File.WriteAllBytes(path, data);

            // Create Fastfile from template for fastlane setup
            if (buildTarget == BuildTarget.iOS)
            {
                string projectPath = Path.Combine(Directory.GetParent(Application.dataPath).FullName, "Builds/iOS");
                string fastFilePath = Path.Combine(Directory.GetParent(Application.dataPath).FullName, "SupportFiles",
                    "Fastfile");
                
                File.Copy(fastFilePath, Path.Combine(projectPath, "Fastfile"));
            }
        }
    }
}